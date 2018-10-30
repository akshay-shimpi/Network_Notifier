package com.theceo_dev.networknotifier.network_notifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theceo_dev.networknotifier.network_notifier_module.NetworkLayout;
import com.theceo_dev.networknotifier.network_notifier_module.NetworkListener;

public class MainActivity extends AppCompatActivity implements NetworkListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NetworkLayout(MainActivity.this, ((ViewGroup) findViewById(android.R.id.content)), this);

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, NetworkLayout.haveNetworkConnection + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void OnNetworkChanged(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
