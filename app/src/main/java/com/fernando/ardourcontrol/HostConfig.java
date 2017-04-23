package com.fernando.ardourcontrol;

import android.content.Intent;

/**
 * Created by fernando on 09.04.17.
 */

public class HostConfig {
    private final int port;

    private static final String PORT_TAG = "port" + Util.randomString();

    public HostConfig(int port) {
        this.port = port;
    }

    public HostConfig(Intent intent) {
        this.port = intent.getIntExtra(PORT_TAG, 0);
    }

    public int getPort() {
        return port;
    }

    public void addToIntent(Intent intent) {
        intent.putExtra(PORT_TAG, port);
    }
}
