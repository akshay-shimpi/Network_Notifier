package com.theceo_dev.networknotifier.network_notifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theceo_dev.networknotifier.network_notifier_module.NetworkLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup viewGroup = findViewById(android.R.id.content);
        new NetworkLayout(MainActivity.this, ((ViewGroup) findViewById(android.R.id.content)));

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, NetworkLayout.haveNetworkConnection + "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
