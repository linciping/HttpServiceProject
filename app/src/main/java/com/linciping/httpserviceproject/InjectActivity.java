package com.linciping.httpserviceproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.linciping.util.inject.InjectView;
import com.linciping.util.inject.MyInject;

public class InjectActivity extends AppCompatActivity {

    @InjectView(id=R.id.txt_inject)
    TextView txt_inject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        MyInject.init(this);
        txt_inject.setText("注入成功");
    }
}
