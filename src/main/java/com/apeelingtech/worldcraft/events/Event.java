package com.apeelingtech.worldcraft.events;

/**
 * Created by christian on 10/9/15.
 */
public class Event {

    public enum Type {
        MOUSE_PRESSED,
        MOUSE_RELEASED,
        MOUSE_MOVED,
        KEY_PRESSED,
        KEY_RELEASED,
        MOUSE_WHEEL_MOVED
    }

    private Type type;
    boolean handled;

    protected Event(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
