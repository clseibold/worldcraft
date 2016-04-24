package com.apeelingtech.worldcraft.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public Sprite sprite = Sprite.air;
	public boolean solid = true;
	public boolean breakable = true;
	protected Level level;
	public int chunk = 1;
	
	public Block(Sprite sprite, boolean solid, int x, int y, Level level, int chunk) {
		setBounds(new Rectangle(x, y, sprite.getWidth(), sprite.getHeight()));
		this.sprite = sprite;
		this.solid = solid;
		this.level = level;
		this.chunk = chunk;
	}
	
	public void render(Graphics g, float interpolation) {
		if (sprite != Sprite.air) {
			sprite.draw(g, x, y, level);
			//sprite.drawWithScale(g, x, y, Resources.tileSize, Resources.tileSize, level);
		}
	}
	
}
