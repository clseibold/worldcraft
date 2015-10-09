package com.apeelingtech.worldcraft.core;

import com.apeelingtech.worldcraft.events.types.MouseMovedEvent;
import com.apeelingtech.worldcraft.events.types.MousePressedEvent;
import com.apeelingtech.worldcraft.events.types.MouseReleasedEvent;
import com.apeelingtech.worldcraft.layers.Layer;
import com.apeelingtech.worldcraft.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 10/9/15.
 */
public class Window extends JFrame {

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

        screen.init();
        run();
    }

    private void run() {
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
        for (int i = 0; i < layerList.size(); i++) {
            layerList.get(i).onRender(g);
        }
    }

    public void addLayer(Layer layer) {
        layerList.add(layer);
    }

}
