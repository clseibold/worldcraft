package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Tinblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Tinblock(int x, int y, Level level, int chunk) {
		super(Sprite.tin, x, y, level, chunk);
	}
	
}
