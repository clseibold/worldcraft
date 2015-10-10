package com.apeelingtech.worldcraft.layers;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.entity.mob.BasicMob;
import com.apeelingtech.worldcraft.events.Event;
import com.apeelingtech.worldcraft.events.EventDispatcher;
import com.apeelingtech.worldcraft.events.types.MouseMovedEvent;
import com.apeelingtech.worldcraft.events.types.MousePressedEvent;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

import java.awt.*;

/**
 * Created by christian on 10/9/15.
 */
public class BlocksLayer extends Layer {

    private Level level;
    private int pressX, pressY, x, y;

    public BlocksLayer(Level level) {
        this.level = level;
        new BasicMob(level.getXOffsetBlocks() + 5, level.getYOffsetBlocks() - 10, level);
    }

    @Override
    public void onEvent(Event event) {
        EventDispatcher eventDispatcher = new EventDispatcher(event);
        eventDispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> mousePressed((MousePressedEvent) e));
        eventDispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> mouseMoved((MouseMovedEvent) e));
    }

    private boolean mousePressed(MousePressedEvent e) {
        pressX = e.getX();
        pressY = e.getY();

        return true;
    }

    private boolean mouseMoved(MouseMovedEvent e) {
        x = e.getX();
        y = e.getY();
        if (e.isDragged()) {
            level.addXOffsetPixels(-(x - pressX));
            level.addYOffsetPixels(-(y - pressY));
            pressX = e.getX();
            pressY = e.getY();
        }

        return e.isDragged();
    }

    @Override
    public void onUpdate() {
        level.tick();
    }

    @Override
    public void onRender(Graphics g) {
        level.render(g, 0.0f, (Game.SIZE.width / Resources.tileSize) + 1, (Game.SIZE.height / Resources.tileSize) + 1);
    }

}
