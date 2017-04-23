package com.fernando.ardourcontrol.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.fernando.ardourcontrol.R;
import com.fernando.ardourcontrol.Util;

/**
 * Created by fernando on 05.04.17.
 */

public class TransportButton extends AppCompatImageButton {
    public enum Type {
        MIDI_PANIC,
        TOGGLE_CLICK,
        TOGGLE_ROLL,
        TOGGLE_LOOP,
        TOGGLE_REC,
        GOTO_START,
        GOTO_END,
    }

    private final Type type;

    public TransportButton(Type type, Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Drawable buttonStyle = ResourcesCompat.getDrawable(getResources(), R.drawable.ardour_button, null);
        Util.setBackground(this, buttonStyle);
        this.type = type;

        //XXX
        Drawable image;
        switch(type) {
            case TOGGLE_LOOP:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_loop, null);
                this.setImageDrawable(image);
                break;
            case TOGGLE_ROLL:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null);
                this.setImageDrawable(image);
                break;
            case TOGGLE_REC:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_rec, null);
                this.setImageDrawable(image);
                break;
            case GOTO_END:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_end, null);
                this.setImageDrawable(image);
                break;
            case GOTO_START:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_start, null);
                this.setImageDrawable(image);
                break;
            case TOGGLE_CLICK:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_click, null);
                this.setImageDrawable(image);
                break;
            case MIDI_PANIC:
                image = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_midi_panic, null);
                this.setImageDrawable(image);
                break;
        }
    }
}
