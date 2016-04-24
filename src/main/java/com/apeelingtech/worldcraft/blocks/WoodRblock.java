package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class WoodRblock extends WoodBlock {
	private static final long serialVersionUID = 1L;
	
	public WoodRblock(int x, int y, Level level, int chunk) {
		super(Sprite.woodR, x, y, level, chunk);
	}
	
}
