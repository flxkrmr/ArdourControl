package com.fernando.ardourcontrol.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.fernando.ardourcontrol.R;

/**
 * Created by fernando on 05.04.17.
 */

public class TransportView extends LinearLayout {

    private boolean enable;

    private Callback handler;

    private TransportButton togglePlayButton;
    private TransportButton toggleLoopButton;
    private TransportButton toggleRecButton;
    private TransportButton gotoStartButton;
    private TransportButton gotoEndButton;
    private TransportButton toggleClickButton;
    private TransportButton midiPanicButton;


    public TransportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.setMinimumWidth(LayoutParams.MATCH_PARENT);
        this.setMinimumHeight(LayoutParams.WRAP_CONTENT);

        initTransportView(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TransportView,
                0, 0);
        try {
            enable = a.getBoolean(R.styleable.TransportView_enableTransportView, true);
        } finally {
            a.recycle();
        }

    }

    public void setHandler(Callback handler) {
        this.handler = handler;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        togglePlayButton.setEnabled(enable);
        toggleLoopButton.setEnabled(enable);
        toggleRecButton.setEnabled(enable);
        gotoStartButton.setEnabled(enable);
        gotoEndButton.setEnabled(enable);
        toggleClickButton.setEnabled(enable);
        midiPanicButton.setEnabled(enable);
    }

    public boolean isEnable() {
        return enable;
    }

    public interface Callback {
        void onTogglePlayButton(View view);
        void onToggleRecButton(View view);
        void onToggleLoopButton(View view);
        void onGotoStartButton(View view);
        void onGotoEndButton(View view);
        void onToggleClickButton(View view);
        void onMidiPanicButton(View view);
    }

    private void initTransportView(Context context) {
        this.setWeightSum(7f);

        togglePlayButton = new TransportButton(TransportButton.Type.TOGGLE_ROLL, context, null);
        togglePlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onTogglePlayButton(view); }
        });
        togglePlayButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        toggleLoopButton = new TransportButton(TransportButton.Type.TOGGLE_LOOP, context, null);
        toggleLoopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onToggleLoopButton(view); }
        });
        toggleLoopButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        toggleRecButton = new TransportButton(TransportButton.Type.TOGGLE_REC, context, null);
        toggleRecButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onToggleRecButton(view); }
        });
        toggleRecButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        gotoStartButton = new TransportButton(TransportButton.Type.GOTO_START, context, null);
        gotoStartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onGotoStartButton(view); }
        });
        gotoStartButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        gotoEndButton = new TransportButton(TransportButton.Type.GOTO_END, context, null);
        gotoEndButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onGotoEndButton(view); }
        });
        gotoEndButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        toggleClickButton = new TransportButton(TransportButton.Type.TOGGLE_CLICK, context, null);
        toggleClickButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onToggleClickButton(view); }
        });
        toggleClickButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        midiPanicButton = new TransportButton(TransportButton.Type.MIDI_PANIC, context, null);
        midiPanicButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { handler.onMidiPanicButton(view); }
        });
        midiPanicButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

        this.addView(togglePlayButton);
        this.addView(toggleLoopButton);
        this.addView(toggleRecButton);
        this.addView(gotoStartButton);
        this.addView(gotoEndButton);
        this.addView(toggleClickButton);
        this.addView(midiPanicButton);
    }
}
