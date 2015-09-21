package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Cobbleblock extends Block {
	private static final long serialVersionUID = 1L;
	
	public Cobbleblock(int x, int y, Level level, int chunk) {
		super(Sprite.cobble, true, x, y, level, chunk);
	}
	
}
