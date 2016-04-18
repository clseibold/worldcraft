package com.apeelingtech.worldcraft.entity.mob;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.blocks.Block;
import com.apeelingtech.worldcraft.entity.Entity;
import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Mob extends Entity {
	
	protected byte health = 100;
	protected boolean falling = false;
	protected boolean jumping = false;
	protected int jumpingTotal = 0;
	public byte hunger = 100;
	
	public Mob(Sprite sprite, double spawnX, double spawnY, int width, int height, Level level) {
		super(sprite, spawnX, spawnY, width, height, level);
	}
	
	@Override
	public void tick() {
		//System.out.println("Ticking");
		if (jumping) {
			if (!move(0.0, -0.07)) { // When hitting an above block
				jumping = false;
				jumpingTotal = 0;
				falling = true;
			}
			jumpingTotal++;
			if (jumpingTotal == 30) {
				jumping = false;
				jumpingTotal = 0;
				falling = true;
			}
		}
        if (!jumping && move(0.0, 0.07)) { // Simple falling
	        falling = true;
        } else {
        	falling = false;
        }
	}

    public boolean move(double x, double y) {
    	double ox = x;
    	double oy = y;
    	Block BLeft = level.getBlock((int)(this.x), (int)(this.y + (this.height / Resources.tileSize)));
    	Block BRight = level.getBlock((int)(this.x + (this.width / Resources.tileSize)), (int)(this.y + (this.height / Resources.tileSize)));
    	if (!BRight.solid && !BLeft.solid) {
    		Block NBLeft = level.getBlock((int)(this.x + x), (int)(this.y + y + (this.height / Resources.tileSize)));
    		Block NBRight = level.getBlock((int)(this.x + x + (this.width / Resources.tileSize)), (int)(this.y + y + (this.height / Resources.tileSize)));
    		while (NBLeft.solid || NBRight.solid) {
    			if (ox > 0) {
    				x -= .01;
    				if (x <= 0) {
    					x = 0;
    				}
    			} else if (ox < 0) {
    				x += .01;
    				if (x >= 0) {
    					x = 0;
    				}
    			}
    			if (oy > 0) {
    				y -= .01;
    				if (y <= 0) {
    					y = 0;
    				}
    			} else if (oy < 0) {
    				y += .01;
    				if (y >= 0) {
    					y = 0;
    				}
    			}
    			if (x == 0 && y == 0) {
    				return false;
    			}
    			NBLeft = level.getBlock((int)(this.x + x), (int)(this.y + y + (this.height / Resources.tileSize)));
    			NBRight = level.getBlock((int)(this.x + x + (this.width / Resources.tileSize)), (int)(this.y + y + (this.height / Resources.tileSize)));
    		}
    		if (!NBLeft.solid && !NBRight.solid){
    			changePositionBy(x, y);
    			return true;
    		}
    	}
    	return false;
    }
	
	@Override
	public void render(Graphics g, float interpolation) {
		sprite.draw(g, x, y, level);
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void startJumping() {
		jumping = true;
	}
	
}
