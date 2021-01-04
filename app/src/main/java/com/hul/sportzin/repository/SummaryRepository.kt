package com.hul.sportzin.repository

import com.hul.sportzin.app.SportsApp
import com.hul.sportzin.database.SportsDatabase
import com.hul.sportzin.model.Players
import com.hul.sportzin.network.NetworkApiClient

class SummaryRepository {

    private val mInstance = SportsApp.mInstance
    private val mDao = SportsDatabase.getDatabase(mInstance).playerDao

    suspend fun getPlayerData() = NetworkApiClient.getNetworkServices().getSummaryData()

    suspend fun insertTeam(players: List<Players>) = mDao.insertAll(players)

    fun deleteData() = mDao.deleteAll()

    fun getAllPlayers() = mDao.getPlayersInfo()

}