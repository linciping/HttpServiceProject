package com.linciping.util.http;

/**
 * Created by Administrator on 2015/11/9 0009.
 */
public interface BaseHttpLister {
    void onSuccess(String result);
    void onError(String error);
}
