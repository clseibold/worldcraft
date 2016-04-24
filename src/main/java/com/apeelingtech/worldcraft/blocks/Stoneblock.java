package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Stoneblock extends Block {
	private static final long serialVersionUID = 1L;
	
	public Stoneblock(int x, int y, Level level, int chunk) {
		super(Sprite.stone, true, x, y, level, chunk);
	}
	
}
