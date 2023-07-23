// IBinderTestExtInterface.aidl
package com.slin.study.natively.binder.ext;

// 尝试Binder的extension功能
interface IBinderTestExtInterface {
    void setExtMsg(String message);
    String getExtMsg();
}