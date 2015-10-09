package com.apeelingtech.worldcraft.events.types;

import com.apeelingtech.worldcraft.events.Event;

/**
 * Created by christian on 10/9/15.
 */
public class MouseMovedEvent extends Event {

    private int x, y;
    private boolean dragged;

    public MouseMovedEvent(int x, int y, boolean dragged) {
        super(Type.MOUSE_MOVED);
        this.x = x;
        this.y = y;
        this.dragged = dragged;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDragged() {
        return dragged;
    }
}
