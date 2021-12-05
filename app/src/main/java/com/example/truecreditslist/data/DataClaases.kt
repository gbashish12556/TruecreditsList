package com.example.truecreditslist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "truecredit_post",
    indices = [Index(value = ["name"], unique = false)])
data class TruecreditsPost(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: Int,
    @SerializedName("is_checked")
    val isChecked: Boolean?

){
    var indexInResponse: Int = -1
}