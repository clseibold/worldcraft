package com.apeelingtech.worldcraft.events.types;

/**
 * Created by christian on 10/9/15.
 */
public class MousePressedEvent extends MouseButtonEvent {

    public MousePressedEvent(int button, int x, int y) {
        super(button, x, y, Type.MOUSE_PRESSED);
    }

}
