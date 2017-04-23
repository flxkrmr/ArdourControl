package com.fernando.ardourcontrol;

import android.content.Intent;

/**
 * Created by fernando on 04.04.17.
 */

public class TargetConfig {
    private final String address;
    private final int port;

    private static final String ADDRESS_TAG = "address" + Util.randomString();
    private static final String PORT_TAG = "port" + Util.randomString();

    public TargetConfig(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public TargetConfig(Intent intent) {
        this.address = intent.getStringExtra(ADDRESS_TAG);
        this.port = intent.getIntExtra(PORT_TAG, 0);
    }

    public String getAddress() {
        return address;
    }
    public int getPort() {
        return port;
    }

    public void addToIntent(Intent intent) {
        intent.putExtra(ADDRESS_TAG, address);
        intent.putExtra(PORT_TAG, port);
    }

}
