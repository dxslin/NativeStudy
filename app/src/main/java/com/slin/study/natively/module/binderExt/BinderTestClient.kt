package com.slin.study.natively.module.binderExt

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.slin.core.logger.logd
import com.slin.core.logger.loge
import com.slin.study.natively.binder.ext.IBinderTestExtInterface
import com.slin.study.natively.binder.ext.IBinderTestInterface

class BinderTestClient(private val context: Context) {

    private var binderExt: IBinderTestExtInterface? = null
    private var binder: IBinderTestInterface? = null
    var isConnected = false
        private set
    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            logd { "onServiceConnected service: $service" }
            isConnected = true
            binder = IBinderTestInterface.Stub.asInterface(service)
            logd { "onServiceConnected binder: $binder" }

            val getExtMd = service::class.java.getMethod("getExtension")
            val ext = getExtMd.invoke(service) as IBinder?
            logd { "onServiceConnected ext: $ext" }
            binderExt = IBinderTestExtInterface.Stub.asInterface(ext)
            logd { "onServiceConnected binderExt: $binderExt" }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            logd { "onServiceConnected" }
            isConnected = false

        }

    }

    fun init() {
        context.bindService(
            Intent(context, BinderTestService::class.java),
            conn,
            Context.BIND_AUTO_CREATE
        )
    }

    fun deinit(){
        context.unbindService(conn)
    }

    fun sendMsg(msg: String?) {
        if (isConnected) {
            binder?.sendMsg(msg)
        } else {
            loge { "service not connected." }
        }
    }

    fun getMsg(): String {
        return if (isConnected) {
            binder?.msg ?: ""
        } else {
            loge { "service not connected." }
            ""
        }
    }

    fun setExtMsg(msg: String) {
        if (isConnected) {
            binderExt?.extMsg = msg
        } else {
            loge { "service not connected." }
        }
    }

    fun getExtMsg(): String {
        return if (isConnected) {
            binderExt?.extMsg ?: ""
        } else {
            loge { "service not connected." }
            ""
        }
    }
}
