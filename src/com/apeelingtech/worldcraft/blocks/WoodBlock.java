package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class WoodBlock extends Block {
	private static final long serialVersionUID = 1L;
	
	public WoodBlock(Sprite sprite, int x, int y, Level level, int chunk) {
		super(sprite, false, x, y, level, chunk);
	}
	
}
