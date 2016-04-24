package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Diamondblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Diamondblock(int x, int y, Level level, int chunk) {
		super(Sprite.diamond, x, y, level, chunk);
	}
	
}
