package com.linciping.util.http;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2015/11/9 0009.
 * 进行非UI线程的请求基类 By HttpClient
 */
public class HttpClientTask extends AsyncTask<Object,Void,HttpModel> {

    private HttpClientLister lister;
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected HttpModel doInBackground(Object... params) {
        String url=params[0].toString();
        HttpClient client= new DefaultHttpClient();
        this.lister= (HttpClientLister) params[1];
        NetOpertion opertion= (NetOpertion) params[2];
        List<NameValuePair> param= (List<NameValuePair>) params[4];
        switch (opertion)
        {
            case DOGET:
                HttpGet get=new HttpGet(url);
                HttpModel model=new HttpModel();
                try {
                    HttpResponse response=client.execute(get);
                    if (response.getStatusLine().getStatusCode()==200)
                    {
                        BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        StringBuffer buffer=new StringBuffer();
                        String line="";
                        while ((line=reader.readLine())!=null)
                        {
                            buffer.append(line);
                        }
                        model.setMessage("success");
                        model.setData(buffer.toString());
                    }
                    else
                    {
                        model.setMessage("error");
                        model.setData("请求失败");
                    }
                    return  model;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DOPOST:
                break;
            case FILEUP:
                break;
            case FILEDOWNLOAD:
                break;
        }
        return null;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param model The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(HttpModel model) {
        if (model==null)
        {
            lister.onError("请求错误");
        }
        else
        {
            if (model.getMessage()=="success")
            {
                lister.onSuccess(model.getData());
            }
            else
            {
                lister.onError(model.getData());
            }
        }
    }
}
