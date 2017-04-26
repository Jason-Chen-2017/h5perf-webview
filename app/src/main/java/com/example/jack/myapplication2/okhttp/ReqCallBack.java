package com.example.jack.myapplication2.okhttp;

/**
 * Created by jack on 2017/4/26.
 */

public interface ReqCallBack<T> {
    /**
     * 响应成功
     */
    void onReqSuccess(T result);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);
}
