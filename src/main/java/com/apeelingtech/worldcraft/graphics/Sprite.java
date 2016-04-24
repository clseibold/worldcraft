package com.apeelingtech.worldcraft.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Sprite {
	
	private int width, height;
	private int x, y;
	private SpriteSheet sheet;
	
	private String path;
	private BufferedImage image;
	
	// Basic Blocks
	public static Sprite dirt = new Sprite(40, 40, 0, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(40, 40, 1, 0, SpriteSheet.tiles);
	public static Sprite sand = new Sprite(40, 40, 2, 0, SpriteSheet.tiles);
	public static Sprite air = new Sprite(40, 40, 3, 0, SpriteSheet.tiles);
	public static Sprite cobble = new Sprite(40, 40, 11, 0, SpriteSheet.tiles);
	
	// Surface Blocks
	public static Sprite chest = new Sprite(40, 40, 2, 3, SpriteSheet.tiles);
	public static Sprite chestOpen = new Sprite(40, 40, 3, 3, SpriteSheet.tiles);
	public static Sprite woodR = new Sprite(40, 40, 4, 0, SpriteSheet.tiles);
	public static Sprite yFlower = new Sprite(40, 40, 6, 0, SpriteSheet.tiles);
	public static Sprite grou = new Sprite(40, 40, 7, 0, SpriteSheet.tiles);
	public static Sprite leaves = new Sprite(40, 40, 8, 0, SpriteSheet.tiles);
	
	// Other Blocks
	public static Sprite leftLadder = new Sprite(40, 40, 0, 3, SpriteSheet.tiles);
	public static Sprite rightLadder = new Sprite(40, 40, 1, 3, SpriteSheet.tiles);
	public static Sprite backPack = new Sprite(40, 40, 0, 4, SpriteSheet.tiles);
	public static Sprite gravel = new Sprite(40, 40, 5, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(40, 40, 10, 0, SpriteSheet.tiles);
	public static Sprite waterF = new Sprite(40, 40, 19, 19, SpriteSheet.tiles);
	
	// Ores
	public static Sprite coal = new Sprite(40, 40, 12, 0, SpriteSheet.tiles);
	public static Sprite copper = new Sprite(40, 40, 13, 0, SpriteSheet.tiles);
	public static Sprite tin = new Sprite(40, 40, 14, 0, SpriteSheet.tiles);
	public static Sprite diamond = new Sprite(40, 40, 15, 0, SpriteSheet.tiles);
	public static Sprite iron = new Sprite(40, 40, 16, 0, SpriteSheet.tiles);
	public static Sprite ruby = new Sprite(40, 40, 17, 0, SpriteSheet.tiles);
	
	// Ore Items
	public static Sprite coalI = new Sprite(40, 40, 12, 1, SpriteSheet.tiles);
	public static Sprite copperI = new Sprite(40, 40, 13, 1, SpriteSheet.tiles);
	public static Sprite tinI = new Sprite(40, 40, 14, 1, SpriteSheet.tiles);
	public static Sprite diamondI = new Sprite(40, 40, 15, 1, SpriteSheet.tiles);
	public static Sprite ironI = new Sprite(40, 40, 16, 1, SpriteSheet.tiles);
	public static Sprite rubyI = new Sprite(40, 40, 17, 1, SpriteSheet.tiles);
	
	// Hurt Blocks
	public static Sprite lavaF = new Sprite(40, 40, 18, 18, SpriteSheet.tiles);
	public static Sprite lavaF2 = new Sprite(40, 40, 19, 18, SpriteSheet.tiles);
	
	// Crafting Blocks and Tools
	public static Sprite stick = new Sprite(40, 40, 19, 0, SpriteSheet.tiles);
	public static Sprite WAxe = new Sprite(40, 40, 9, 0, SpriteSheet.tiles);
	
	// Mobs and Characters
	public static Sprite character = new Sprite(40, 80, 0, 0, SpriteSheet.character);
	public static Sprite characterR = new Sprite(40, 80, 1, 0, SpriteSheet.character);
	public static Sprite characterS = new Sprite(40, 80, 2, 0, SpriteSheet.character);
	public static Sprite characterShift = new Sprite(40, 80, 3, 0, SpriteSheet.character);
	public static Sprite mobBasic = new Sprite(40, 80, 0, 0, SpriteSheet.mobBasic);
	public static Sprite mobBasicR = new Sprite(40, 80, 1, 0, SpriteSheet.mobBasic);
	public static Sprite mobBasicS = new Sprite(40, 80, 2, 0, SpriteSheet.mobBasic);
	public static Sprite mobBasicShift = new Sprite(40, 80, 3, 0, SpriteSheet.mobBasic);
	
	// Single Sprites
	public static Sprite invCell = new Sprite("/textures/tile_cell.png");
	public static Sprite invCellSelected = new Sprite("/textures/tile_select.png");
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
	}
	
	public Sprite(String path) {
		this.path = path;
		try {
			image = ImageIO.read(Sprite.class.getResource(path));
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public SpriteSheet getSheet() {
		return sheet;
	}
	
	/**
	 * If the Sprite does not have a SpriteSheet, then this image will return the image used for the sprite. If it does have a SpriteSheet, then it's image will be returned.
	 */
	public BufferedImage getImage() {
		if (sheet == null) {
			return image;
		} else {
			return sheet.getImage();
		}
	}
	
	/**
	 * Returns true if the sprite has a sheet provided, otherwise false!
	 */
	public boolean hasSheet() {
		return sheet != null;
	}
	
	/**
	 * Returns the path of the Sprite!
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Draws an image on the screen using a location not relevant to a level.
	 * @param g The graphics object
	 * @param screenX X position on the screen (separate from in-level)
	 * @param screenY Y position on the screen (separate from in-level)
	 */
	public void draw(Graphics g, int screenX, int screenY) {
		g.drawImage(getImage(), screenX, screenY, screenX + width, screenY + height, this.x, this.y, this.x + this.width, this.y + this.width, null);
	}

	/**
	 * Draws an image on the screen using a location not relevant to a level.
	 * The width and height are different from the sprite's width and height.
	 * @param g The graphics object
	 * @param screenX X position on the screen (separate from in-level)
	 * @param screenY Y position on the screen (separate from in-level)
	 * @param width Width to be drawn (does not change sprite's width)
	 * @param height Height to be drawn (does not change sprite's height)
	 */
	public void drawWithScale(Graphics g, int screenX, int screenY, int width, int height) {
		int nX = x * width;
		int nY = y * height;
		
		g.drawImage(getImage(), nX, nY, nX + width, nY + height, screenX, screenY, screenX + this.width, screenY + this.height, null);
	}

	/**
	 * Draws an image into the level. The location inside the level in pixels minus
	 * the offset gives the proper location on-screen.
	 * @param g The graphics object
	 * @param x X position in the level (in blocks). Converted into pixels by this method.
	 * @param y Y position in the level (in blocks). Converted into pixels by this method.
	 * @param level The level the sprite will be drawn into. Need this to get the level's offsets.
	 */
	public void draw(Graphics g, double x, double y, Level level) {
		int nX = ((int) x * Resources.tileSize) + (int)((x - (int) x) * Resources.tileSize); // Position in pixels
		int nY = ((int) y * Resources.tileSize) + (int)((y - (int) y) * Resources.tileSize);
		//g.drawImage(image, screenx, screeny, screenx + width, screeny + height, picx, picy, picx + width, picy + height, null);
		g.drawImage(getImage(), nX - level.getXOffsetPixels(), nY - level.getYOffsetPixels(), nX + width - level.getXOffsetPixels(), nY + height - level.getYOffsetPixels(), this.x, this.y, this.x + this.width, this.y + this.height, null);
	}

	/**
	 * Draws an image into the level, and scales it to a size different from the sprite's size.
	 * The location inside the level in pixels minus the offset gives the proper location on-screen.
	 * @param g The graphics object
	 * @param x X position in the level (in blocks). Converted into pixels by this method.
	 * @param y Y position in the level (in blocks). Converted into pixels by this method.
	 * @param width Width to be drawn (does not change sprite's width)
	 * @param height Height to be drawn (does not change sprite's height)
	 * @param level The level the sprite will be drawn into. Need this to get the level's offsets.
	 */
	public void drawWithScale(Graphics g, double x, double y, int width, int height, Level level) {
		int nX = ((int) x * Resources.tileSize) + (int)((x - (int) x) * Resources.tileSize); // Convert's blocks to pixels
		int nY = ((int) y * Resources.tileSize) + (int)((y - (int) y) * Resources.tileSize);
		int nSX = level.getXOffsetPixels();
		int nSY = level.getYOffsetPixels();
		
		g.drawImage(getImage(), nX - nSX, nY - nSY, nX + width - nSX, nY + height - nSY, this.x, this.y, this.x + this.width, this.y + this.height, null);
	}
	
}
