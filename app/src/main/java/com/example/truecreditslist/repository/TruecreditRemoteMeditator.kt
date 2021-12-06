package com.example.truecreditslist.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.truecreditslist.api.TruecreditsApi
import com.example.truecreditslist.data.TruecreditsPost
import com.example.truecreditslist.data.TruecreditsRemoteKey
import com.example.truecreditslist.db.TruecreditsDao
import com.example.truecreditslist.db.TruecreditsDb
import com.example.truecreditslist.db.TruecreditsRemotekeyDao
import retrofit2.HttpException
import retrofit2.await
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db: TruecreditsDb,
    private val truecreditsApi: TruecreditsApi,
) : RemoteMediator<Int, TruecreditsPost>() {
    private val postDao: TruecreditsDao = db.posts()
    private val remoteKeyDao: TruecreditsRemotekeyDao = db.remoteKey()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TruecreditsPost>
    ): MediatorResult {
        try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // Query DB for SubredditRemoteKey for the subreddit.
                    // SubredditRemoteKey is a wrapper object we use to keep track of page keys we
                    // receive from the Reddit API to fetch the next or previous page.
                    val remoteKey = db.withTransaction {
                        remoteKeyDao.remoteKeyByPost(1)
                    }

                    // We must explicitly check if the page key is null when appending, since the
                    // Reddit API informs the end of the list by returning null for page key, but
                    // passing a null key to Reddit API will fetch the initial page.
                    if (remoteKey.nextPageKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextPageKey
                }
            }

            var pageNo = if (loadKey == null) 1 else loadKey

            val data = truecreditsApi.getListData(
                pageNo = pageNo,
                perPage = 20,
            ).await()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteAllRemoteKeys()
                    postDao.deleteAllPosts()
                }
                remoteKeyDao.insert(TruecreditsRemoteKey(1,pageNo+1))
                postDao.insertAll(data)
            }

            return MediatorResult.Success(endOfPaginationReached = data.isEmpty())

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}
