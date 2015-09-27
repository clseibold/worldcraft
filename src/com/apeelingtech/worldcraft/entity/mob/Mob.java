package com.apeelingtech.worldcraft.entity.mob;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.blocks.Airblock;
import com.apeelingtech.worldcraft.blocks.Block;
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
        move(0.05, 0.05);
	}

    public void move(double x, double y) {
        Block block = level.getBlock((int)Math.floor(this.x + this.width), (int)Math.floor(this.y + this.height));
        if (x + this.x > this.x && y + this.y > this.y) {
            block = level.getBlock((int)Math.floor(this.x + this.width + x), (int)Math.floor(this.y + this.height + y));
            System.out.println("TESTING");
        }
        if (block instanceof Airblock) {
            changePositionBy(x, y);
        }
    }
	
	@Override
	public void render(Graphics g, float interpolation) {
		sprite.draw(g, x, y, level);
	}
	
}
