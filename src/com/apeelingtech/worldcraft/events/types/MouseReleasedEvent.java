package com.apeelingtech.worldcraft.events.types;

/**
 * Created by christian on 10/9/15.
 */
public class MouseReleasedEvent extends MouseButtonEvent {

    public MouseReleasedEvent(int button, int x, int y) {
        super(button, x, y, Type.MOUSE_RELEASED);
    }

}
