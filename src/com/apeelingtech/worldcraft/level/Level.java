package com.apeelingtech.worldcraft.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.blocks.Airblock;
import com.apeelingtech.worldcraft.blocks.AnimatedBlock;
import com.apeelingtech.worldcraft.blocks.Block;
import com.apeelingtech.worldcraft.blocks.Coalblock;
import com.apeelingtech.worldcraft.blocks.Copperblock;
import com.apeelingtech.worldcraft.blocks.Diamondblock;
import com.apeelingtech.worldcraft.blocks.Dirtblock;
import com.apeelingtech.worldcraft.blocks.Grassblock;
import com.apeelingtech.worldcraft.blocks.Gravelblock;
import com.apeelingtech.worldcraft.blocks.Ironblock;
import com.apeelingtech.worldcraft.blocks.Rubyblock;
import com.apeelingtech.worldcraft.blocks.Solidairblock;
import com.apeelingtech.worldcraft.blocks.Stoneblock;
import com.apeelingtech.worldcraft.blocks.Tinblock;
import com.apeelingtech.worldcraft.blocks.Waterf;
import com.apeelingtech.worldcraft.entity.Entity;
import com.apeelingtech.worldcraft.graphics.Sprite;
import com.apeelingtech.worldcraft.util.Resources;
import com.apeelingtech.worldcraft.util.Vector2;

public class Level {
	
	public final int worldWidth = 2000, worldHeight = 2000;
	private volatile ArrayList<Block> blocks = new ArrayList<>();
	private volatile ArrayList<Entity> entities = new ArrayList<>();
	private int xOffset = 0, yOffset = 0, chunk = 1; // Offset of blocks, in pixels
	private Random rand;
    private Vector2 composition[];
	private int startPositions[];
	private boolean scrolling = false;
	private int scrollToX = 0; // in Pixels
	private int scrollToY = 0; // in Pixels
	private double scrollSpeed = 10;
	
	public Level(long seed) {
		rand = new Random();
		rand.setSeed(seed);

        int percents[] = {5, 23, 27, 30, 30, 20, 20, 500};
        int total = 0;
        for (int i : percents) {
            total += i;
        }
        composition = new Vector2[percents.length];
        int last = 0;
        for (int i = 0; i < percents.length; i++) {
            composition[i] = new Vector2(last, last + percents[i]);
            last += percents[i];
            composition[i].scale(total, 100);
        }
		startPositions = new int[worldWidth];
		for (int x = 1; x < startPositions.length; x++) {
			// startPositions[x] = (()/5) +
		}

        generateLevel();
    }
	
	private void generateLevel() {
		int currentChunk = 0;
		for (int x = 0; x < worldWidth; x++) {
			if (x % 20.0 == 0.0) { // Use something similar to this for biomes!!!
				currentChunk++;
			}
			boolean firstPlaced = true;
			int amtDirt = 0;
			for (int y = 0; y < worldHeight; y++) {
				// Generating earth
				if (y == 0 || x == 0 || y == worldHeight || x == worldWidth) {
					blocks.add(new Solidairblock(x, y, this, currentChunk));
				} else if (y > ((worldHeight - 2) / 4) - (rand.nextInt(2) + 4)) { // (worldHeight - 2) / 4? 1/4 of the world will be sky!
					if (firstPlaced) {
                        // Place water or first block if first block has not been placed
						if (rand.nextInt(100) > 80) { // 20% chance? (make < 20 instead of > 80) TODO
							// Water if by a grass, dirt, or water block on left or top side, otherwise air
							if (blocks.get(y + (x - 1) * worldHeight) instanceof Waterf || blocks.get((y - 1) + x * worldHeight) instanceof Waterf) {
								blocks.add(new Waterf(x, y, this, currentChunk));
							} else if ((blocks.get(y + (x - 1) * worldHeight) instanceof Dirtblock || blocks.get(y + (x - 1) * worldHeight) instanceof Grassblock) && rand.nextInt(100) > 50) {
								blocks.add(new Waterf(x, y, this, currentChunk));
								// firstPlaced = false; // Maybe keep!
							} else {
								blocks.add(new Airblock(x, y, this, currentChunk));
							}
						} else {
							blocks.add(new Grassblock(x, y, this, currentChunk)); // Water Check here! Turn to dirt/sand if water above TODO
                            // Tell that the first block in column/x-pos has been placed
							firstPlaced = false;
						}
					} else {
                        // First block has already been placed.
						if (amtDirt >= rand.nextInt(3) + 2) { // If current amt of dirt placed is above a number between 2 and 5, place ores/stone
                            int chance = rand.nextInt(100);
                            for (int i = 0; i < composition.length; i++) {
                                if (chance >= composition[i].getX() && chance <= composition[i].getY()) {
                                    switch(i) {
                                        case 0:
                                            blocks.add(new Gravelblock(x, y, this, currentChunk));
                                            break;
                                        case 1:
                                            blocks.add(new Coalblock(x, y, this, currentChunk));
                                            break;
                                        case 2:
                                            blocks.add(new Ironblock(x, y, this, currentChunk));
                                            break;
                                        case 3:
                                            blocks.add(new Copperblock(x, y, this, currentChunk));
                                            break;
                                        case 4:
                                            blocks.add(new Tinblock(x, y, this, currentChunk));
                                            break;
                                        case 5:
                                            blocks.add(new Rubyblock(x, y, this, currentChunk));
                                            break;
                                        case 6:
                                            blocks.add(new Diamondblock(x, y, this, currentChunk));
                                            break;
                                        case 7:
                                            blocks.add(new Stoneblock(x, y, this, currentChunk));
                                            break;
                                        default:
                                            blocks.add(new Stoneblock(x, y, this, currentChunk));
                                    }
                                    break;
                                }
                            }

							/*if (rand.nextInt(100) > 12) { // 88% chance, Stone
								blocks.add(new Stoneblock(x, y, this, currentChunk));
							} else if (y > worldHeight - (worldHeight / 4) + 100 && rand.nextInt(100) > 5) { // if near bottom of world; 95% chance, Lava
								blocks.add(new Lavaf(x, y, this, currentChunk));
							} else if (rand.nextInt(100) > 57) {
								blocks.add(new Coalblock(x, y, this, currentChunk));
							} else if (rand.nextInt(100) > 73) {
								blocks.add(new Ironblock(x, y, this, currentChunk));
							} else if (y > (worldHeight / 2) - 100 && rand.nextInt(100) > 70) {
								blocks.add(new Copperblock(x, y, this, currentChunk));
							} else if (y > (worldHeight / 2) - 100 && rand.nextInt(100) > 70) {
								blocks.add(new Tinblock(x, y, this, currentChunk));
							} else if (y > (worldHeight / 2) + (worldHeight / 4) && rand.nextInt(100) > 80) {
								blocks.add(new Rubyblock(x, y, this, currentChunk));
							} else if (y > (worldHeight / 2) + (worldHeight / 4) && rand.nextInt(100) > 80) {
								blocks.add(new Diamondblock(x, y, this, currentChunk));
							} else {
								blocks.add(new Gravelblock(x, y, this, currentChunk));
							}*/
						} else {
							blocks.add(new Dirtblock(x, y, this, currentChunk)); // Water check here!
						}
						amtDirt++;
					}
				} else {
					if (blocks.get(y + (x - 1) * worldHeight) instanceof Waterf || blocks.get((y - 1) + x * worldHeight) instanceof Waterf) {
						blocks.add(new Waterf(x, y, this, currentChunk));
					} else {
						blocks.add(new Airblock(x, y, this, currentChunk));
					}
				}
			}
		}
	}
	
	public void tick() {
		/*
		//if (this.getXOffsetBlocks() >= 0 && (xOffset / Resources.tileSize) + (Game.SIZE.width / Resources.tileSize) <= worldWidth) {
			//if (getYOffsetBlocks() >= 0 && (yOffset / Resources.tileSize) + (Game.SIZE.height / Resources.tileSize) <= worldHeight) {
				chunk = blocks.get((int)getXOffsetBlocks() + (int)getYOffsetBlocks() * worldWidth).chunk;
			//}
		//}
		
		// System.out.println(chunk + " : " + ((double)xOffset / (double)Resources.tileSize) + " : " + ((double)yOffset / (double)Resources.tileSize));
		for (int x = (20 * chunk) - 40; x <= (20 * chunk) - 40 + 20; x++) {
			if (x < 0 || x + (Game.SIZE.width / Resources.tileSize) > worldWidth) {
				continue;
			}
			for (int y = 0; y < worldHeight; y++) {
				if (y < 0 || y + (Game.SIZE.height / Resources.tileSize) > worldHeight) {
					continue;
				}
				Block block = blocks.get((x * worldHeight) + y);
				if (block instanceof AnimatedBlock) {
					((AnimatedBlock) block).tick();
				}

			}
		}*/
		
		if (scrolling) { // Should this accelerate? TODO
			if (xOffset > scrollToX) {
				xOffset += -scrollSpeed;
			} else if (xOffset < scrollToX) {
				xOffset += scrollSpeed;
			}
			if (yOffset > scrollToY) {
				yOffset += -scrollSpeed;
			} else if (yOffset < scrollToY) {
				yOffset += scrollSpeed;
			}
			
			if (xOffset != scrollToX && xOffset <= scrollToX + scrollSpeed && xOffset >= scrollToX - scrollSpeed) {
				xOffset = scrollToX;
			}
			if (yOffset != scrollToY && yOffset <= scrollToY + scrollSpeed && yOffset >= scrollToY - scrollSpeed) {
				yOffset = scrollToY;
			}
			
			if (xOffset == scrollToX && yOffset == scrollToY) {
				scrolling = false;
				scrollToX = 0;
				scrollToY = 0;
			}
		}
		
		for (Entity entity : entities) {
			entity.tick();
		}
	}
	
	public void render(Graphics g, float interpolation, int renW, int renH) {
		for (int y = (yOffset / Resources.tileSize); y < (yOffset / Resources.tileSize) + renH; y++) {
			if (y < 0 || y + (Game.SIZE.height / Resources.tileSize) > worldHeight) {
				continue;
			}
			for (int x = (xOffset / Resources.tileSize); x < (xOffset / Resources.tileSize) + renW; x++) {
				if (x < 0 || x + (Game.SIZE.width / Resources.tileSize) > worldWidth) {
					continue;
				}
				blocks.get(y + x * worldHeight).render(g, interpolation);
			}
		}
		
		for (Entity entity : entities) {
			entity.render(g, interpolation);
		}
		
		// Cursor
		// if (!game.inventory.isOpen && !game.isPaused && !game.character.isDead) {
		// if (!(block[x][y] instanceof Airblock) && !(block[x][y] instanceof Solidairblock)) {
		// if (block[x][y].contains(new Point((game.mse.x / Game.pixelSize) + (int) game.sX, (game.mse.y / Game.pixelSize) + (int) game.sY))) {
		// g.setColor(new Color(255, 255, 255, 20));
		// g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
		// }
		// }
		// }
	}

	/**
	 * Adds provided entity to entities list in level.
	 * @param entity Entity to be added.
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * Removes provided entity from entities list in level. TODO
	 * @param entity Entity to be removed.
	 */
	public void removeEntity(Entity entity) { entities.remove(entity); } // Will this work!?!?

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y) {
        return blocks.get(y + x * worldHeight);
    }

    /*public Block getBlock(double x, double y) {
        return blocks.get((int)Math.floor(y + x * worldHeight));
    }*/

    public Block getBlock(int i) {
        return blocks.get(i);
    }

    public Block getBlock(double i) {
        return blocks.get((int)i);
    }

	public int getXOffsetPixels() {
		return xOffset;
	}

	public int getYOffsetPixels() {
		return yOffset;
	}

	public double getXOffsetBlocks() {
		return xOffset / Resources.tileSize;
	}

	public double getYOffsetBlocks() {
		return yOffset / Resources.tileSize;
	}

	public void addOffsetPixels(int xOffset, int yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}

	public void addXOffsetPixels(int xOffset) {
		this.xOffset += xOffset;
	}

	public void addYOffsetPixels(int yOffset) {
		this.yOffset += yOffset;
	}

	public void addOffsetBlocks(double xOffset, double yOffset) {
		this.xOffset += xOffset * Resources.tileSize;
		this.yOffset += yOffset * Resources.tileSize;
	}
	
	public void addXOffsetBlocks(double xOffset) {
		this.xOffset += xOffset * Resources.tileSize;
	}

	public void addYOffsetBlocks(double yOffset) {
		this.yOffset += yOffset * Resources.tileSize;
	}

	public void setOffsetBlocks(double xOffset, double yOffset) {
		this.xOffset = (int)(xOffset * Resources.tileSize);
		this.yOffset = (int)(yOffset * Resources.tileSize);
	}
	
	public void setXOffsetBlocks(double xOffset) {
		this.xOffset = (int) (xOffset * Resources.tileSize);
	}
	
	public void setYOffsetBlocks(double yOffset) {
		this.yOffset = (int)(yOffset * Resources.tileSize);
	}
	
	public void setOffsetPixels(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void setXOffsetPixels(int xOffset) {
		this.xOffset = xOffset;
	}
	
	public void setYOffsetPixels(int yOffset) {
		this.yOffset = yOffset;
	}
	
	public void setOffsetCenterBlock(double xOffset, double yOffset) {
		this.xOffset = (int) ((xOffset + Game.SIZE.width / Resources.tileSize / 2) * Resources.tileSize);
		this.yOffset = (int) ((yOffset + Game.SIZE.width / Resources.tileSize / 2) * Resources.tileSize);
	}
	
	public void setOffsetCenterPixel(int xOffset, int yOffset) {
		this.xOffset = xOffset + Game.SIZE.width / 2;
		this.yOffset = yOffset + Game.SIZE.width / 2;
	}
	
	public void scrollToBlock(double xOffset, double yOffset) { // TODO
		scrolling = true;
		scrollToX = (int)(xOffset * Resources.tileSize);
		scrollToY = (int)(yOffset * Resources.tileSize);
	}
	
	public void scrollToPixel(int xOffset, int yOffset) { // TODO
		scrolling = true;
		scrollToX = xOffset;
		scrollToY = yOffset;
	}
	
}
