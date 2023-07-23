package com.slin.study.natively.module.binderExt

import com.slin.study.natively.binder.ext.IBinderTestInterface

class BinderTestImpl : IBinderTestInterface.Stub() {

    var mMessage = "default"

    override fun sendMsg(message: String?) {
        mMessage = message ?: "null"
    }

    override fun getMsg(): String {
        return mMessage
    }
}