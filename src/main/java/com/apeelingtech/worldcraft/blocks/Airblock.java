package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Airblock extends Block {
	private static final long serialVersionUID = 1L;
	
	public Airblock(int x, int y, Level level, int currentChunk) {
		super(Sprite.air, false, x, y, level, currentChunk);
		breakable = false;
	}
	
}
