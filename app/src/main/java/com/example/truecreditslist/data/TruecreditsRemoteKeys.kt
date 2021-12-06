package com.example.truecreditslist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "remote_keys")
data class TruecreditsRemoteKey(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    val nextPageKey: Int
)
