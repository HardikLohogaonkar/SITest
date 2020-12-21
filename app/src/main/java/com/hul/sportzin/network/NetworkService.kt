package com.hul.sportzin.network


import com.hul.sportzin.model.SummaryData
import com.hul.sportzin.network.NetworkConstant.GET_SUMMARY
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET(GET_SUMMARY)
    suspend fun getSummaryData(): Response<SummaryData>
}