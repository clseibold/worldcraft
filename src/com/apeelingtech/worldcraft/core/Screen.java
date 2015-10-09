package com.apeelingtech.worldcraft.core;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by christian on 10/9/15.
 */
public class Screen extends Canvas {

    private BufferStrategy bs;
    private Graphics g;

    public Screen(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void init() {
        createBufferStrategy(3);
    }

    public void beginRendering() {
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void clear() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public Graphics getGraphicsObject() {
        return g;
    }

    public void endRendering() {
        g.dispose();
        bs.show();
    }

}
