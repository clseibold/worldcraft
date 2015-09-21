package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Grassblock extends GroundBlock {
	private static final long serialVersionUID = 1L;
	
	public Grassblock(int x, int y, Level level, int chunk) {
		super(Sprite.grass, x, y, level, chunk);
	}
	
}
