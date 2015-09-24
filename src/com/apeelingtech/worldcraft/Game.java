package com.apeelingtech.worldcraft;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.apeelingtech.worldcraft.entity.mob.BasicMob;
import com.apeelingtech.worldcraft.input.Listener;
import com.apeelingtech.worldcraft.level.Level;
import com.apeelingtech.worldcraft.util.Resources;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public int fps = 0;
	public boolean paused = false;
	public static final Dimension SIZE = new Dimension(800, 600);
	public BufferedImage image = new BufferedImage(SIZE.width, SIZE.height, BufferedImage.TYPE_INT_RGB);
	
	private int frameCount = 0;
	public boolean running = false;
	private static final String TITLE = "World Craft 3.1";
	private Thread thread;
	// private Frame frame; - Converted to local field (in Constructor)
	private Listener input;
	
	public BasicMob basicMob;

	public Level level;
	
	public Game() {
		Frame frame;
		setSize(SIZE);
		thread = new Thread(this, "Main");
		frame = new Frame();
		input = new Listener(this);

		// Listeners
		frame.addWindowListener(input);
		addMouseListener(input);
		addKeyListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
		
		frame.setTitle(TITLE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
	
	public synchronized void start() {
		init();
		running = true;
		thread.start();
	}
	
	public synchronized void init() {
		// Define objects here
		level = new Level(new Random().nextLong());
		basicMob = new BasicMob(level.getXOffsetBlocks(), level.getYOffsetBlocks(), level);
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		requestFocus();
		
		final double GAME_HERTZ = 30.0; // How many ticks per second. Default is 30, original is 60.
		// Calculate how many ns each frame should take for our target game hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new render.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime;
		lastRenderTime = System.nanoTime();
		
		// If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		
		// Simple way of finding FPS
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;
			
			if (!paused) {
				// Do as many game updates as we need to, potentially playing catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					tick();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}
				
				// If for some reason an update takes forever, we don't want to do an insane number of catchups.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}
				
				// Render. To do so, we need to calculate interpolation for a smooth render.
				float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
				// System.out.println(interpolation + " [interpolation]");
				render(interpolation);
				lastRenderTime = now;
				
				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					// System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					fps = frameCount;
					frameCount = 0;
					lastSecondTime = thisSecond;
				}
				
				// Yield until it has been at least the target time between render. This saves the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();
					
					// This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					now = System.nanoTime();
				}
			}
		}
		stop();
	}
	
	// boolean firstRender = true;
	
	public void tick() {
		level.tick();
		
		if (input.down && basicMob.getY() < level.worldHeight - 1) {
			// level.addYOffsetPixels(10);
			basicMob.changePositionBy(0.0, 0.05);
		} else if (input.up && basicMob.getY() > 1.0) {
			// level.addYOffsetPixels(-10);
			basicMob.changePositionBy(0.0, -0.05);
		}
		if (input.right && basicMob.getY() < level.worldWidth - 1) { // Do eventually, make stop at end of level
			// level.addXOffsetPixels(10);
			basicMob.changePositionBy(0.05, 0.0);
		} else if (input.left && basicMob.getX() > 1.0) {
			basicMob.changePositionBy(-0.05, 0.0);
			if (level.getXOffsetBlocks() > 0.5) { // Why does it have to be .5 for the first block (the solid air block)???
				level.addXOffsetBlocks(-0.05);
			}
		}
		if (input.dragging) {
			level.addXOffsetPixels(-(input.X - input.pressX));
			level.addYOffsetPixels(-(input.Y - input.pressY));
			input.pressX = input.X;
			input.pressY = input.Y;
		}
	}
	
	public void render(float interpolation) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, SIZE.width, SIZE.height, null);
		
		render(g, interpolation);
		
		g.dispose();
		bs.show();
	}
	
	public void render(Graphics g, float interpolation) {
		g.setColor(Color.white);
		// Rendering and Drawing area
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, SIZE.width, SIZE.height);
		
		level.render(g, interpolation, (SIZE.width / Resources.tileSize) + 1, (SIZE.height / Resources.tileSize) + 1);
		
		frameCount++;
	}
	
}
