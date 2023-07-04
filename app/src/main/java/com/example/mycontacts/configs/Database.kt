package com.example.mycontacts.configs

import android.content.Context
import androidx.room.Room

class Database {
    companion object{
        fun datebase(context: Context): AppDatabase {
            val db =
                Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "appDataBase"
                ).build()
            return db
        }
    }
}