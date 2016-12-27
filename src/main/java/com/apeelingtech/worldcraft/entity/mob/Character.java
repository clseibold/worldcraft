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
	public void tick() { // Should I put left and right movement in here?

		if (jumping) {
			if (move(0.0, -0.099)) { // TODO: -0.09
				if (y >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize) - 4) {
					level.scrollToBlock(x /* + whatever currently moving at */ - (Game.SIZE.width / 2 / Resources.tileSize), y - (Game.SIZE.height / 2 / Resources.tileSize));
		        }
			} else { // When hitting an above block
				jumping = false;
				jumpingTotal = 0;
				falling = true;
			}
			jumpingTotal++;
			if (jumpingTotal == 20) {
				jumping = false;
				jumpingTotal = 0;
				falling = true;
			}
		}
        if (!jumping && move(0.0, 0.099)) { // Simple falling Def: 0.07
	        if (y >= level.getYOffsetBlocks() + (Game.SIZE.height / Resources.tileSize) - 4) {
	        	level.scrollToBlock(x /* + whatever currently moving at */ - (Game.SIZE.width / 2 / Resources.tileSize), y - (Game.SIZE.height / 2 / Resources.tileSize));
	        }
	        falling = true;
        } else {
        	falling = false;
        }
	}

}
