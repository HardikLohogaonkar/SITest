package com.hul.sportzin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hul.sportzin.model.Players
import com.hul.sportzin.model.SummaryData
import com.hul.sportzin.repository.SummaryRepository
import com.hul.sportzin.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SummaryViewModel(private val mSummaryRepository: SummaryRepository) : ViewModel() {

    val mSummaryData = MutableLiveData<Resource<SummaryData>>()

    fun getSummaryData() =
        viewModelScope.launch(Dispatchers.IO) {

            try {
                mSummaryData.postValue(Resource.Loading())
                val response = mSummaryRepository.getPlayerData()
//            val mTeamData = mSummaryRepository.insertTeam()
                mSummaryData.postValue(setData(response))
            } catch (e: Exception) {
                e.message.toString()
            }
        }

    private fun setData(response: Response<SummaryData>): Resource<SummaryData> {

        if (response.isSuccessful) {
            response.body()?.let { it ->
                return Resource.Success(it)
            }
        }
        return Resource.Error(data = null, message = response.message())
    }

    fun getTeamAPlayers(mList: List<Players>): List<Players> {

        return if (mList.isNotEmpty()) {

            val team = mList[0].teamName
            var teamAList = mList.filter { it.teamName == team }

            teamAList
        } else {
            emptyList()
        }
    }

    fun getTeamBPlayers(mList: List<Players>): List<Players> {

        return if (mList.isNotEmpty()) {

            val team = mList[0].teamName
            var teamBList = mList.filter { it.teamName != team }

            teamBList
        } else {
            emptyList()
        }
    }

    fun addTeamData(players: List<Players>) = viewModelScope.launch {
        mSummaryRepository.insertTeam(players)
    }

    fun deleteData() = mSummaryRepository.deleteData()

    fun getTeamData() = mSummaryRepository.getAllPlayers()


}