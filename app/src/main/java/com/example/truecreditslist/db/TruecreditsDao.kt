package com.example.truecreditslist.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.truecreditslist.data.TruecreditsPost

@Dao
interface TruecreditsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<TruecreditsPost>)

    @Query("SELECT * FROM truecredit_post WHERE name = :name ORDER BY indexInResponse ASC")
    fun postsBySubreddit(name: String): PagingSource<Int, TruecreditsPost>

}
