package com.apeelingtech.worldcraft;

import java.awt.Dimension;
import java.util.Random;

import com.apeelingtech.worldcraft.core.Window;
import com.apeelingtech.worldcraft.layers.BlocksLayer;
import com.apeelingtech.worldcraft.level.Level;

public class Game /*extends Canvas implements Runnable*/ {
	
	public static final Dimension SIZE = new Dimension(800, 600);
	private static final String TITLE = "World Craft 3.1";

	public static void main(String[] args) {
		// new Game().start();
		Level level = new Level(new Random().nextLong());
		Window window = new Window(TITLE, 800, 600);
		window.addLayer(new BlocksLayer(level));
	}

/*
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
	}*/
	
}
