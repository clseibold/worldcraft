package com.apeelingtech.worldcraft.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private BufferedImage image;
	private String path;
	private int width = 0, height = 0;
	
	// SpriteSheets
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/tileset_terrain.png");
	public static SpriteSheet character = new SpriteSheet("/textures/sheets/character.png");
	public static SpriteSheet mobBasic = new SpriteSheet("/textures/sheets/baseMob.png");
	
	public SpriteSheet(String path) {
		this.path = path;
		load();
	}
	
	private void load() {
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
