package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Lavaf extends AnimatedBlock {
	private static final long serialVersionUID = 1L;
	
	public Lavaf(int x, int y, Level level, int chunk) {
		super(false, x, y, level, chunk, Sprite.lavaF, Sprite.lavaF2);
		breakable = false;
	}
	
}
