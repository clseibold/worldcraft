package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Leafblock extends Block {
	private static final long serialVersionUID = 1L;
	
	public Leafblock(int x, int y, Level level, int chunk) {
		super(Sprite.leaves, false, x, y, level, chunk);
	}
	
}
