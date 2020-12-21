package com.hul.sportzin.network


import com.hul.sportzin.BuildConfig
import com.hul.sportzin.network.NetworkConstant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkApiClient {

    private var mNetworkServices: NetworkService? = null

    private var httpClient: OkHttpClient.Builder? = null

    /**
     *  build retrofit object
     */
    private fun getRetrofitClient() {
        httpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            readTimeout(5000, TimeUnit.SECONDS)
            writeTimeout(5000, TimeUnit.SECONDS)
            connectTimeout(5000, TimeUnit.SECONDS)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient!!.build())
            .build()

        mNetworkServices = retrofit.create(NetworkService::class.java)
    }


    /**
     * To connect network service
     */
    fun getNetworkServices(): NetworkService {
        return if (mNetworkServices != null) {
            mNetworkServices as NetworkService
        } else {
            getRetrofitClient()
            mNetworkServices as NetworkService
        }
    }
}