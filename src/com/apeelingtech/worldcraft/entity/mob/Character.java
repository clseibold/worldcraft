package com.apeelingtech.worldcraft.entity.mob;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Character extends Mob {

	public Character(double spawnX, double spawnY, Level level) {
		super(Sprite.character, spawnX, spawnY, Sprite.character.getWidth(), Sprite.character.getHeight(), level);
	}
	
	@Override
	public void tick() {
		//System.out.println("Ticking");
		if (jumping) {
			if (move(0.0, -0.07)) {
				if (y >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize) - 4) {
		        	/*level.setYOffsetBlocks(y);
		        	level.addYOffsetPixels(-Game.SIZE.height / 2);*/
					level.scrollToBlock(level.getXOffsetBlocks(), y - (Game.SIZE.height / 2 / Resources.tileSize));
		        }
			} else { // When hitting an above block
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
	        if (y >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize) - 4) {
	        	/*level.setYOffsetBlocks(y);
	        	level.addYOffsetPixels(-Game.SIZE.height / 2);*/
	        	level.scrollToBlock(level.getXOffsetBlocks(), y - (Game.SIZE.height / 2 / Resources.tileSize));
	        }
	        falling = true;
        } else {
        	falling = false;
        }
	}

}
