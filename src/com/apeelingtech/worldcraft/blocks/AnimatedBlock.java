package com.apeelingtech.worldcraft.blocks;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;

public class AnimatedBlock extends Block {
	private static final long serialVersionUID = 1L;
	
	public static byte changeCount = 0;
	public static final byte changeTime = 90; // 2 seconds (because 30 ticks per second)
	
	final Sprite[] sprites;
	Sprite currentSprite;
	
	public boolean isOpen = false;
	
	AnimatedBlock(boolean solid, int x, int y, Level level, int chunk, Sprite... sprites) {
		super(sprites[0], solid, x, y, level, chunk);
		this.sprites = sprites;
		currentSprite = sprites[0];
	}
	
	AnimatedBlock(boolean solid, int x, int y, Level level, int currentSpriteIndex, int chunk, Sprite... sprites) {
		super(sprites[0], solid, x, y, level, chunk);
		this.sprites = sprites;
		currentSprite = sprites[currentSpriteIndex];
	}
	
	public void tick() {
		if (changeCount >= changeTime) {
			for (int i = 0; i < sprites.length; i++) {
				if (currentSprite == sprites[i]) {
					if ((i + 1) == sprites.length) {
						currentSprite = sprites[0];
					} else {
						currentSprite = sprites[i + 1];
					}
					break;
				}
			}
			changeCount = 0;
		} else {
			changeCount++;
		}
	}
	
	@Override
	public void render(Graphics g, float interpolation) {
		if (currentSprite != Sprite.air) {
			currentSprite.draw(g, x, y, level);
		}
	}
	
}
