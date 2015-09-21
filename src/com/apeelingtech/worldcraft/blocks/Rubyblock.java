package com.apeelingtech.worldcraft.blocks;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class Rubyblock extends OreBlock {
	private static final long serialVersionUID = 1L;
	
	public Rubyblock(int x, int y, Level level, int chunk) {
		super(Sprite.ruby, x, y, level, chunk);
	}
	
}
