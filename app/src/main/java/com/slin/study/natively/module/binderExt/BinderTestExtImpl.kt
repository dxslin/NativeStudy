package com.slin.study.natively.module.binderExt

import com.slin.study.natively.binder.ext.IBinderTestExtInterface


class BinderTestExtImpl : IBinderTestExtInterface.Stub() {
    private var mMessage = "default_ext_message"

    override fun setExtMsg(message: String) {
        mMessage = message
    }

    override fun getExtMsg(): String {
        return mMessage
    }
}