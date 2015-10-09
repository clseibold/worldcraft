package com.apeelingtech.worldcraft.layers;

import com.apeelingtech.worldcraft.events.Event;
import com.apeelingtech.worldcraft.events.EventListener;

import java.awt.*;

/**
 * Created by christian on 10/9/15.
 */
public abstract class Layer implements EventListener {

    @Override
    public abstract void onEvent(Event event);

    public abstract void onUpdate();

    public abstract void onRender(Graphics g);

}
