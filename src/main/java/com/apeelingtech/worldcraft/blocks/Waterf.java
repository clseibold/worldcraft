package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Waterf extends Block {
	private static final long serialVersionUID = 1L;
	
	public Waterf(int x, int y, Level level, int chunk) {
		super(Sprite.waterF, false, x, y, level, chunk);
		breakable = false;
	}
	
}
