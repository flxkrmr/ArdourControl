package com.fernando.ardourcontrol;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by fernando on 04.04.17.
 */

public class Util {

    public static String randomString() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("deprecation")
    public static void setBackground(View view, Drawable drawable) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
