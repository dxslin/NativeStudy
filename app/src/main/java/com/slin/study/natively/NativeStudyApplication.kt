package com.slin.study.natively

import android.app.Application
import com.slin.core.SCore
import com.slin.core.logger.initLogger

class NativeStudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SCore.initLogger(BuildConfig.DEBUG)
    }
}