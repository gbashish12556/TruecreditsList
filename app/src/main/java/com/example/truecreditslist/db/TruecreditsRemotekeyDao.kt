package com.example.truecreditslist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.truecreditslist.data.TruecreditsRemoteKey

@Dao
interface TruecreditsRemotekeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: TruecreditsRemoteKey)

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()


    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun remoteKeyByPost(id: Int): TruecreditsRemoteKey

}