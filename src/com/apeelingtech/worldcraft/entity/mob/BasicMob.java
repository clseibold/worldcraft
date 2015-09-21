package com.apeelingtech.worldcraft.entity.mob;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class BasicMob extends Mob {
	
	public BasicMob(double spawnX, double spawnY, Level level) {
		super(Sprite.mobBasic, spawnX, spawnY, Sprite.mobBasic.getWidth(), Sprite.mobBasic.getHeight(), level);
	}
	
}
