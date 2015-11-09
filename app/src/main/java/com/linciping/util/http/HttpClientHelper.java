package com.linciping.util.http;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by Administrator on 2015/11/9 0009.
 * HttpClient帮助类
 */
public class HttpClientHelper {

    public static void doGet(String url,HttpClientLister lister)
    {
        HttpClientTask task=new HttpClientTask();
        task.execute(url,lister,NetOpertion.DOGET,null);
    }

    public static void doPost(String url,List<NameValuePair> params,HttpClientLister lister)
    {
        HttpClientTask task=new HttpClientTask();
        task.execute(url,lister,NetOpertion.DOPOST,params);
    }

    public static void  doFileUp(String url,HttpClientLister lister)
    {

    }
}
