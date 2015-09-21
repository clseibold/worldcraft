package com.apeelingtech.worldcraft.entity.mob;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.entity.Entity;
import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Mob extends Entity {
	
	protected byte health = 100;
	public byte hunger = 100;
	
	public Mob(Sprite sprite, double spawnX, double spawnY, int width, int height, Level level) {
		super(sprite, spawnX / (sprite.getWidth() / Resources.tileSize), spawnY / (sprite.getHeight() / Resources.tileSize), width, height, level);
	}
	
	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics g, float interpolation) {
		sprite.draw(g, x, y, level);
	}
	
}
