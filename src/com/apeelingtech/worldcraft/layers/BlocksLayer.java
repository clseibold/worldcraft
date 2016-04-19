package com.apeelingtech.worldcraft.layers;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.entity.mob.Character;
import com.apeelingtech.worldcraft.events.Event;
import com.apeelingtech.worldcraft.events.EventDispatcher;
import com.apeelingtech.worldcraft.events.types.*;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Created by christian on 10/9/15.
 */
public class BlocksLayer extends Layer {

    private Level level;
    private int pressX, pressY, x, y, pressButton;
    private boolean dragged = false;
    
    // Will be eventually moved into level
    private Character character;
    private boolean[] keys;

    public BlocksLayer(Level level) {
        this.level = level;
        character = new Character(6.5, ((level.worldHeight - 2) / 4) - 10, level);
        level.setOffsetBlocks(character.getX(), character.getY());
        level.addOffsetPixels(-Game.SIZE.width / 2, -Game.SIZE.height / 2);
        keys = new boolean[255];
    }

    @Override
    public void onEvent(Event event) {
        EventDispatcher eventDispatcher = new EventDispatcher(event);
        eventDispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> mousePressed((MousePressedEvent) e));
        eventDispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> mouseReleased((MouseReleasedEvent) e));
        eventDispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> mouseMoved((MouseMovedEvent) e));
        eventDispatcher.dispatch(Event.Type.KEY_PRESSED, (Event e) -> keyPressed((KeyPressedEvent) e));
        eventDispatcher.dispatch(Event.Type.KEY_RELEASED, (Event e) -> keyReleased((KeyReleasedEvent) e));
        eventDispatcher.dispatch(Event.Type.MOUSE_WHEEL_MOVED, (Event e) -> mouseWheelMoved((MouseWheelMovedEvent) e));
    }

    private boolean mousePressed(MousePressedEvent e) {
        pressX = e.getX();
        pressY = e.getY();
        pressButton = e.getButton();

        return true;
    }

    private boolean mouseReleased(MouseReleasedEvent e) {
        level.mouseReleased(pressX, pressY, pressButton, dragged);

        dragged = false;
        return true;
    }

    private boolean mouseMoved(MouseMovedEvent e) {
        x = e.getX();
        y = e.getY();
        if (e.isDragged()) {
        	level.addOffsetPixels(-(x - pressX), -(y - pressY));
        	// Make mob positions in pixels (do same with their movement)
            // mob1.move(-(x - pressX) / Resources.tileSize, -(y - pressY) / Resources.tileSize);
            pressX = e.getX();
            pressY = e.getY();
        }

        dragged = e.isDragged();

        return e.isDragged();
    }
    
    private boolean mouseWheelMoved(MouseWheelMovedEvent e) {
    	Resources.tileSize += e.getWheelRotation();
    	return true;
    }

    private boolean keyPressed(KeyPressedEvent e) {
    	keys[e.getKeyCode()] = true;
    	return true;
    }

    private boolean keyReleased(KeyReleasedEvent e) {
    	keys[e.getKeyCode()] = false;
    	return true;
    }
    
    @Override
    public void onUpdate() {
        if (keys[KeyEvent.VK_UP] && !character.isFalling()) {
        	character.startJumping();
        	keys[KeyEvent.VK_UP] = false;
        }
        if (keys[KeyEvent.VK_LEFT]) {
        	if (character.move(-.15, 0)) {
        		if (character.getX() <= level.getXOffsetBlocks() + (Game.SIZE.width / Resources.tileSize / 2) + 1) {
        			if (character.getX() >= level.getXOffsetBlocks()) {
        				if (character.getY() <= level.getYOffsetBlocks() || character.getY() >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize)) {
        					level.scrollToBlock(character.getX() - (Game.SIZE.width / 2 / Resources.tileSize), character.getY() - (Game.SIZE.height / 2 / Resources.tileSize));
        				} else {
        					level.addXOffsetBlocks(-.15);
        				}
        			} else {
        				level.scrollToBlock(character.getX() - (Game.SIZE.width / 2 / Resources.tileSize), character.getY() - (Game.SIZE.height / 2 / Resources.tileSize));
        			}
        		}
        	}
        } else if (keys[KeyEvent.VK_RIGHT]) {
        	if (character.move(.15, 0)) {
        		if (character.getX() >= level.getXOffsetBlocks() + (Game.SIZE.width / Resources.tileSize / 2) - 1) {
        			if (character.getX() <= level.getXOffsetBlocks() + (Game.SIZE.width / Resources.tileSize) + 1) {
        				if (character.getY() <= level.getYOffsetBlocks() || character.getY() >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize)) {
        					level.scrollToBlock(character.getX() - (Game.SIZE.width / 2 / Resources.tileSize), character.getY() - (Game.SIZE.height / 2 / Resources.tileSize));
        				} else {
        					level.addXOffsetBlocks(.15);
        				}
        			} else {
        				level.scrollToBlock(character.getX() - (Game.SIZE.width / 2 / Resources.tileSize), character.getY() - (Game.SIZE.height / 2 / Resources.tileSize));
        			}
        		}
        	}
        }
        
        level.tick();
    }

    @Override
    public void onRender(Graphics g) {
        level.render(g, 0.0f, (Game.SIZE.width / Resources.tileSize) + 1, (Game.SIZE.height / Resources.tileSize) + 1);
    }

}
