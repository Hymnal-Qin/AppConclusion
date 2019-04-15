package com.zzdc.workpolicy

import com.qxj.commonbase.BaseApplication

class App : BaseApplication() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}