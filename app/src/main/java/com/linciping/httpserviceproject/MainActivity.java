package com.linciping.httpserviceproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.btn_ToHttpClient)
    Button btnToHttpClient;
    @InjectView(R.id.btn_HttpUrlConnection)
    Button btnHttpUrlConnection;
    @InjectView(R.id.btn_toInjectActivity)
    Button btnToInjectActivity;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = MainActivity.this;

        btnToHttpClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HttpClientActivity.class);
                startActivity(intent);
            }
        });

        btnHttpUrlConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HttpClientActivity.class);
                startActivity(intent);
            }
        });

        btnToInjectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, InjectActivity.class);
                startActivity(intent);
            }
        });
    }
}
