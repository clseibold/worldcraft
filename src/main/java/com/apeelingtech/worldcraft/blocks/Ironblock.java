package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Ironblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Ironblock(int x, int y, Level level, int chunk) {
		super(Sprite.iron, x, y, level, chunk);
	}
	
}
