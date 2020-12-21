package com.hul.sportzin.app

import android.app.Application

class SportsApp :Application() {

    companion object {

        lateinit var mInstance: SportsApp
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
    }
}