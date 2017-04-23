package com.fernando.ardourcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TargetConfig targetConfig;
    private HostConfig hostConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XXX until there is a configuration activity and some persistence
        targetConfig = new TargetConfig("192.168.1.101", 3819);

        hostConfig = new HostConfig(6667);
    }

    public void onConfigActivity(View view) {

    }

    public void onTransportActivity(View view) {
        Intent intent = new Intent(this, TransportActivity.class);
        targetConfig.addToIntent(intent);
        hostConfig.addToIntent(intent);
        startActivity(intent);
    }
}
