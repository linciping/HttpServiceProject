package com.linciping.httpserviceproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.linciping.util.http.HttpClientHelper;
import com.linciping.util.http.HttpClientLister;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HttpClientActivity extends AppCompatActivity {

    @InjectView(R.id.btn_HttpGetClient)
    Button btnHttpGetClient;
    @InjectView(R.id.btn_HttpPostClient)
    Button btnHttpPostClient;
    @InjectView(R.id.txt_valueClient)
    TextView txtValueClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);
        ButterKnife.inject(this);

        btnHttpGetClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeNow= Long.toString(System.currentTimeMillis());
                String beHotTime=createBeHotTime(System.currentTimeMillis());
                String url="http://toutiao.com/api/article/recent/?source=2&count=20&category=news_world&max_behot_time="+beHotTime+"&utm_source=toutiao&offset=0&_="+timeNow;
                HttpClientHelper.doGet(url, new HttpClientLister() {
                    @Override
                    public void onSuccess(String result) {
                        txtValueClient.setText(result);
                    }

                    @Override
                    public void onError(String error) {
                        txtValueClient.setText(error);
                    }
                });
//                 RequestTask task=new RequestTask();
//                task.execute(url);
                //                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message message=Message.obtain();
//                        message.what=0;
//                        mHandler.sendMessage(message);
//                    }
//                }).start();
            }
        });
    }

    private class RequestTask extends AsyncTask<String,Void,String>
    {

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
        protected String doInBackground(String... params) {
            String url=params[0];
            HttpClient client=new DefaultHttpClient();
            HttpGet get=new HttpGet(url);
            try {
                HttpResponse response=client.execute(get);
                if(response.getStatusLine().getStatusCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    String NL = System.getProperty("line.separator");
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + NL);
                    }
                    String result = buffer.toString();
                    return result;
                }
                else
                {
                    return "请求失败";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            txtValueClient.setText(s);
        }
    }

//    private Handler mHandler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what)
//            {
//                case 0:
//                    HttpClient client=new DefaultHttpClient();
//                    long nowTime=System.currentTimeMillis();
//                    String timeNow= Long.toString(System.currentTimeMillis());
//                    String beHotTime=createBeHotTime(System.currentTimeMillis());
//                    HttpGet get=new HttpGet("http://toutiao.com/api/article/recent/?source=2&count=20&category=news_world&max_behot_time="+beHotTime+"&utm_source=toutiao&offset=0&_="+timeNow);
//                    try {
//                        HttpResponse response=client.execute(get);
//                        if(response.getStatusLine().getStatusCode() == 200) {
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                            StringBuffer buffer = new StringBuffer();
//                            String line = "";
//                            String NL = System.getProperty("line.separator");
//                            while ((line = reader.readLine()) != null) {
//                                buffer.append(line + NL);
//                            }
//                            String result = buffer.toString();
//                            txtValueClient.setText(result);
//                        }
//                        else
//                        {
//                            txtValueClient.setText("请求失败");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                case 1:
//                    txtValueClient.setText(msg.obj.toString());
//                    break;
//            }
//        }
//    };

    private String createBeHotTime(Long timeNow)
    {
        long tage=timeNow+30000;
        String str_tag=Long.toString(tage);
        String last=str_tag.substring(10,12);
        String newstr=str_tag.substring(0,10);
        String result=newstr+"."+last;
        return  result;
    }
}
