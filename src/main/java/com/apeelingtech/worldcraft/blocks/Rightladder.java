package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Rightladder extends Block {
	private static final long serialVersionUID = 1L;
	
	public Rightladder(int x, int y, Level level, int chunk) {
		super(Sprite.rightLadder, false, x, y, level, chunk);
	}
	
}
