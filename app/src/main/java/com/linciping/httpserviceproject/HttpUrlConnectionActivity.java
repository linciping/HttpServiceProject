package com.linciping.httpserviceproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HttpUrlConnectionActivity extends AppCompatActivity {

    @InjectView(R.id.btn_HttpGetUrl)
    Button btnHttpGetUrl;
    @InjectView(R.id.btn_HttpPostUrl)
    Button btnHttpPostUrl;
    @InjectView(R.id.txt_valueUrl)
    TextView txtValueUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);
        ButterKnife.inject(this);
    }
}
