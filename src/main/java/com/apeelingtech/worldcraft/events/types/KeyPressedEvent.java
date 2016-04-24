package com.apeelingtech.worldcraft.events.types;

/**
 * Created by krixa on 4/16/2016.
 */
public class KeyPressedEvent extends KeyEvent {

    public KeyPressedEvent(int keyCode) {
        super(keyCode, Type.KEY_PRESSED);
    }

}
