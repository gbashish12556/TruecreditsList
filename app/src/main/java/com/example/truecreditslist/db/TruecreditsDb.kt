package com.example.truecreditslist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.truecreditslist.data.TruecreditsPost


@Database(
    entities = [TruecreditsPost::class],
    version = 1,
    exportSchema = false
)
abstract class TruecreditsDb : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): TruecreditsDb {
            val databaseBuilder = Room.databaseBuilder(context, TruecreditsDb::class.java, "truecredits.db")
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun posts(): TruecreditsDao
}