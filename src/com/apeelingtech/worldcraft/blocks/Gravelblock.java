package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Gravelblock extends GroundBlock {
	private static final long serialVersionUID = 1L;
	
	public Gravelblock(int x, int y, Level level, int chunk) {
		super(Sprite.gravel, x, y, level, chunk);
	}
	
}
