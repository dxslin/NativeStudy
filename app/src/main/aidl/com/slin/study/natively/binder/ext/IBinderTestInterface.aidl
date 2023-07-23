// IBinderTestInterface.aidl
package com.slin.study.natively.binder.ext;

// AIDL 测试
// https://developer.android.google.cn/guide/components/aidl?hl=zh-cn
// https://source.android.google.cn/docs/core/architecture/aidl/aidl-backends?hl=zh-cn

interface IBinderTestInterface {
    void sendMsg(@nullable String message);
    String getMsg();
}