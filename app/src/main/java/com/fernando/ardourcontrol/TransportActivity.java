package com.fernando.ardourcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fernando.ardourcontrol.views.TransportView;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class TransportActivity extends AppCompatActivity {
    private static final String TAG = "TransportActivity";

    private OSCSession osc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        Intent intent = this.getIntent();
        TargetConfig targetConfig = new TargetConfig(intent);
        HostConfig hostConfig = new HostConfig(intent);

        try {
            osc = new OSCSession(targetConfig, hostConfig);
        } catch (SocketException | UnknownHostException e) {
            setTransportEnabled(false);
            Log.e(TAG, "Error opening OSCSession", e);
        }

        ((TransportView)this.findViewById(R.id.transport_view)).setHandler(this.transportViewCallback);
        setTransportEnabled(true);
    }

    private void setTransportEnabled(boolean enabled) {
        this.findViewById(R.id.add_marker).setEnabled(enabled);
        this.findViewById(R.id.prev_marker).setEnabled(enabled);
        this.findViewById(R.id.next_marker).setEnabled(enabled);
        this.findViewById(R.id.cancel_all_solos).setEnabled(enabled);
        this.findViewById(R.id.undo).setEnabled(enabled);
        this.findViewById(R.id.redo).setEnabled(enabled);
        this.findViewById(R.id.save_state).setEnabled(enabled);
        this.findViewById(R.id.transport_view).setEnabled(enabled);
    }

    private final TransportView.Callback transportViewCallback = new TransportView.Callback() {
        @Override
        public void onTogglePlayButton(View view) {
            Log.d(TAG, "OnToggleRoll");
            osc.send(getResources().getString(R.string.toggle_roll), HANDLER);
        }

        @Override
        public void onToggleRecButton(View view) {
            Log.d(TAG, "OnToggleLoop");
            osc.send(getResources().getString(R.string.rec_enable_toggle), HANDLER);
        }

        @Override
        public void onToggleLoopButton(View view) {
            Log.d(TAG, "OnToggleLoop");
            osc.send(getResources().getString(R.string.loop_toggle), HANDLER);
        }

        @Override
        public void onGotoStartButton(View view) {
            Log.d(TAG, "OnGotoStart");
            osc.send(getResources().getString(R.string.goto_start), HANDLER);
        }

        @Override
        public void onGotoEndButton(View view) {
            Log.d(TAG, "OnGotoEnd");
            osc.send(getResources().getString(R.string.goto_end), HANDLER);
        }

        @Override
        public void onToggleClickButton(View view) {
            Log.d(TAG, "OnToggleClick");
            osc.send(getResources().getString(R.string.toggle_click), HANDLER);
        }

        @Override
        public void onMidiPanicButton(View view) {
            Log.d(TAG, "OnMidiPanic");
            osc.send(getResources().getString(R.string.midi_panic), HANDLER);
        }
    };

    public void onJumpBarNeg(View view) {
        Log.d(TAG, "OnJumpBarNeg");
        //osc.send(getResources().getString(R.string.transport_stop), HANDLER);
    }

    public void onJumpBarPos(View view) {
        Log.d(TAG, "OnJumpBarPos");
        //osc.send(getResources().getString(R.string.transport_stop), HANDLER);
    }

    public void onAddMarker(View view) {
        Log.d(TAG, "OnAddMarker");
        //osc.send("/strip/list", HANDLER);
        osc.send(getResources().getString(R.string.add_marker), HANDLER);
    }

    public void onNextMarker(View view) {
        Log.d(TAG, "OnNextMarker");
        osc.send(getResources().getString(R.string.next_marker), HANDLER);
    }

    public void onPrevMarker(View view) {
        Log.d(TAG, "OnPrevMarker");
        osc.send(getResources().getString(R.string.prev_marker), HANDLER);
    }

    public void onCancelAllSolos(View view) {
        Log.d(TAG, "OnCancelAllSolos");
        osc.send(getResources().getString(R.string.cancel_all_solos), HANDLER);
    }

    public void onUndo(View view) {
        Log.d(TAG, "OnUndo");
        osc.send(getResources().getString(R.string.undo), HANDLER);
    }

    public void onRedo(View view) {
        Log.d(TAG, "OnRedo");
        osc.send(getResources().getString(R.string.redo), HANDLER);
    }

    public void onSaveState(View view) {
        Log.d(TAG, "OnSaveState");
        osc.send(getResources().getString(R.string.save_state), HANDLER);
    }

    private static final OSCSession.SendTaskErrorHandler HANDLER =
            new OSCSession.SendTaskErrorHandler() {
                @Override
                public void onError(Exception e) {
                    Log.e("Error sending message", e.getMessage());
                }
            };
}
