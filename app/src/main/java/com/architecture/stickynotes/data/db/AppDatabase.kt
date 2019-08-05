package com.architecture.stickynotes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architecture.stickynotes.data.db.entities.Quote
import com.architecture.stickynotes.data.db.entities.QuoteDAO
import com.architecture.stickynotes.data.db.entities.User

@Database(
    entities = [User::class, Quote::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao(): UserDAO

    abstract fun getQuoteDao(): QuoteDAO

    companion object{

        @Volatile
        private var instanace: AppDatabase? = null

        private val LOCK = Any()


        operator fun invoke(context: Context) = instanace ?: synchronized(LOCK){

            instanace?:buildDatabase(context).also {
                instanace = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase.db"
        ).build()

    }

}