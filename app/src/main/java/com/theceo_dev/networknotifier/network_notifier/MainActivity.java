package com.theceo_dev.networknotifier.network_notifier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.theceo_dev.networknotifier.network_notifier_module.NetworkLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup viewGroup = ((ViewGroup) findViewById(android.R.id.content));
        new NetworkLayout(MainActivity.this, ((ViewGroup) findViewById(android.R.id.content)));
    }
}
