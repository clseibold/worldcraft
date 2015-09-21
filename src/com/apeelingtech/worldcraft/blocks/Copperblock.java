package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Copperblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Copperblock(int x, int y, Level level, int chunk) {
		super(Sprite.copper, x, y, level, chunk);
	}
	
}
