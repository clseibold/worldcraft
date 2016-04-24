package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Dirtblock extends GroundBlock {
	private static final long serialVersionUID = 1L;
	
	public Dirtblock(int x, int y, Level level, int chunk) {
		super(Sprite.dirt, x, y, level, chunk);
	}
	
}
