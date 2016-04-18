package com.apeelingtech.worldcraft.events.types;

import com.apeelingtech.worldcraft.events.Event;

/**
 * Created by krixa on 4/16/2016.
 */
public class KeyReleasedEvent extends KeyEvent {

    public KeyReleasedEvent(int keyCode) {
        super(keyCode, Event.Type.KEY_RELEASED);
    }

}
