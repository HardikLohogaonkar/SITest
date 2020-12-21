package com.hul.sportzin.database

import android.content.Context
import androidx.room.*
import com.hul.sportzin.database.dao.PlayerDao
import com.hul.sportzin.model.Players


@Database(entities = [Players::class], version = 1, exportSchema = false)
abstract class SportsDatabase : RoomDatabase() {

    abstract val playerDao: PlayerDao


    companion object {
        @Volatile
        private var INSTANCE: SportsDatabase? = null

        fun getDatabase(context: Context): SportsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SportsDatabase::class.java, "player"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

}