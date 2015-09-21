package com.apeelingtech.worldcraft.entity;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public abstract class Entity {
	
	protected double x = 0, y = 0; // location in blocks. Double because location can be in between the start of blocks.
	protected int width = 0, height = 0; // Should this be a double to allow a fraction of a block?
	protected boolean removed = false;
	protected Level level;
	protected Sprite sprite;
	
	public Entity(Sprite sprite, double x, double y, int width, int height, Level level) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.level = level;
		this.sprite = sprite;
		level.addEntity(this);
	}
	
	public abstract void tick();
	
	protected void remove() {
		removed = true;
	}
	
	public final boolean isRemoved() {
		return removed;
	}
	
	public abstract void render(Graphics g, float interpolation);

	/**
	 * Sets entity's position in blocks
	 * @param x X position in blocks
	 * @param y Y position in blocks
	 */
	public final void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds to the X and Y positions
	 * @param x Amount to be added to X position, in blocks
	 * @param y Amount to be added to Y position, in blocks
	 */
	public final void changePositionBy(int x, int y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * Removes entity from it's current level. Changes entity's current level to provided level and adds the entity to it.
	 * @param level Level to change to
	 */
	public final void changeLevel(Level level) { // Make removeEntity() in level, make this method remove entity from current level, change level, and add entity to new level.
		this.level = level;
	}
	
	public final double getX() {
		return x;
	}
	
	public final double getY() {
		return y;
	}
	
	public final int getWidth() {
		return width;
	}
	
	public final int getHeight() { return height; }
	
}
