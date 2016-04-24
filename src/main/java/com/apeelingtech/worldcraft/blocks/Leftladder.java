package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Leftladder extends Block {
	private static final long serialVersionUID = 1L;
	
	public Leftladder(int x, int y, Level level, int chunk) {
		super(Sprite.leftLadder, false, x, y, level, chunk);
	}
	
}
