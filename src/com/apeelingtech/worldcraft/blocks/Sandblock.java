package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Sandblock extends GroundBlock {
	private static final long serialVersionUID = 1L;
	
	public Sandblock(int x, int y, Level level, int chunk) {
		super(Sprite.sand, x, y, level, chunk);
	}
	
}
