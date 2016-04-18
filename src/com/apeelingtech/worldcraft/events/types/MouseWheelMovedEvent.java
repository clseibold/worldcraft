package com.apeelingtech.worldcraft.events.types;

import com.apeelingtech.worldcraft.events.Event;

public class MouseWheelMovedEvent extends Event {

	protected int wheelRotation;
	
	public MouseWheelMovedEvent(int wheelRotation) {
		super(Type.MOUSE_WHEEL_MOVED);
	}
	
	public int getWheelRotation() {
		return wheelRotation;
	}
	
}
