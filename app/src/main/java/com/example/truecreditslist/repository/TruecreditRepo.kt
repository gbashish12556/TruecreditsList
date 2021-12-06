package com.example.truecreditslist.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.truecreditslist.api.TruecreditsApi
import com.example.truecreditslist.db.TruecreditsDb

class TruecreditRepo(val db: TruecreditsDb, val truecreditsApi: TruecreditsApi) : TruecreditPostRepo {

    @OptIn(ExperimentalPagingApi::class)
    override fun postOfTrueCredits(pageNo: Int, pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = PageKeyedRemoteMediator(db, truecreditsApi)
    ) {
        db.posts().allPosts()
    }.flow


}
