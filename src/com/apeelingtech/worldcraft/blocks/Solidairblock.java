package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Solidairblock extends Block {
	private static final long serialVersionUID = 1L;
	
	public Solidairblock(int x, int y, Level level, int chunk) {
		super(Sprite.air, true, x, y, level, chunk);
		breakable = false;
	}
	
}
