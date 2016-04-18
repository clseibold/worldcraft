package com.apeelingtech.worldcraft.core;

import com.apeelingtech.worldcraft.events.types.*;
import com.apeelingtech.worldcraft.layers.Layer;
import com.apeelingtech.worldcraft.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 10/9/15.
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Screen screen;
    private List<Layer> layerList = new ArrayList<>();

    public Window(String name, int width, int height) {
        screen = new Screen(width, height);

        setTitle(name);
        add(screen);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        screen.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }
        });

        screen.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
                onEvent(event);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
                onEvent(event);
            }
        });

        screen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                KeyPressedEvent event = new KeyPressedEvent(e.getKeyCode());
                onEvent(event);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyReleasedEvent event = new KeyReleasedEvent(e.getKeyCode());
                onEvent(event);
            }
        });
        
        screen.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelMovedEvent event = new MouseWheelMovedEvent(e.getWheelRotation());
				onEvent(event);
			}
        	
        });

        screen.init();
        run();
    }

    private void run() {
    	//requestFocus();
    	onUpdate();
        screen.beginRendering();
        screen.clear();
        onRender(screen.getGraphicsObject());
        screen.endRendering();
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> run());
    }

    public void onEvent(Event event) {
        for (int i = layerList.size() - 1; i >= 0; i--) {
            layerList.get(i).onEvent(event);
        }
    }

    private void onRender(Graphics g) {
        for (Layer layer : layerList) {
        	layer.onRender(g);
        }
    }
    
    private void onUpdate() {
    	for (Layer layer : layerList) {
    		layer.onUpdate();
    	}
    }

    public void addLayer(Layer layer) {
        layerList.add(layer);
    }

}
