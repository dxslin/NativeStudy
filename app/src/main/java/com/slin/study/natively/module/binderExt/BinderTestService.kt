package com.slin.study.natively.module.binderExt

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.slin.core.logger.logd

class BinderTestService : Service() {

    private val binderTestExt:BinderTestExtImpl by lazy { BinderTestExtImpl() }
    private val binderTest:BinderTestImpl by lazy {
        BinderTestImpl().apply {
            val setExtMd = javaClass.getMethod("setExtension", IBinder::class.java)
            setExtMd.invoke(this, binderTestExt)
            logd { "setExtension: $binderTestExt" }

            val getExtMd = javaClass.getMethod("getExtension")
            val extBinder = getExtMd.invoke(this) as Binder
            logd { "getExtension: $extBinder" }
        }
    }

    override fun onCreate() {
        super.onCreate()
        logd { "onCreate" }

    }

    override fun onBind(intent: Intent?): IBinder {
        logd { "onBind" }
        return binderTest
    }

    override fun onDestroy() {
        super.onDestroy()
        logd { "onDestroy" }
    }
}
