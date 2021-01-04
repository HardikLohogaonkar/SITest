package com.hul.sportzin.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hul.sportzin.model.Players

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players: List<Players>)

    @Query("SELECT * FROM player_info")
    fun getPlayersInfo(): LiveData<List<Players>>

    @Query("DELETE FROM player_info")
    fun deleteAll()

}