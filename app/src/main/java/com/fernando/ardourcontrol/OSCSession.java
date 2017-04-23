package com.fernando.ardourcontrol;

import android.os.AsyncTask;
import android.util.Log;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCPortOut;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPacket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created by fernando on 04.04.17.
 */

public class OSCSession {
    private static final String TAG = "OSCSession";

    private final OSCPortOut portOut;
    private final OSCPortIn portIn;

    public OSCSession(TargetConfig targetConfig, HostConfig hostConfig) throws SocketException, UnknownHostException {
        InetAddress address = InetAddress.getByName(targetConfig.getAddress());
        DatagramSocket socket = new DatagramSocket(hostConfig.getPort());
        portOut = new OSCPortOut(address, targetConfig.getPort(), socket);
        portIn = new OSCPortIn(socket);
        portIn.addListener("/*", new ReceiveTask());
        //portIn.startListening();
    }

    public void send(String address, SendTaskErrorHandler handler) {
        OSCPacket packet = new OSCMessage(address);
        new SendTask(handler).execute(packet);
    }

    public void send(String address, Collection<Object> data, SendTaskErrorHandler handler) {
        OSCPacket packet = new OSCMessage(address, data);
        new SendTask(handler).execute(packet);
    }

    public interface SendTaskErrorHandler {
        void onError(Exception e);
    }

    private class ReceiveTask implements OSCListener {

        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            Log.d(TAG, "received OSC message " + message);
        }
    }

    private class SendTask extends AsyncTask<OSCPacket, Integer, Exception> {

        private final Collection<SendTaskErrorHandler> handlers;

        protected SendTask(SendTaskErrorHandler... handlers) {
            this.handlers = Arrays.asList(handlers);
        }

        @Override
        protected Exception doInBackground(OSCPacket... packets) {
            Log.d(TAG, "Started SendTask");
            for(OSCPacket packet : packets) {
                try {
                    portOut.send(packet);
                } catch (IOException e) {
                    return e;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            if(e != null)
                for(SendTaskErrorHandler handler : handlers)
                    handler.onError(e);
        }
    }
}
