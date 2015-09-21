package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Coalblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Coalblock(int x, int y, Level level, int chunk) {
		super(Sprite.coal, x, y, level, chunk);
	}
	
}
