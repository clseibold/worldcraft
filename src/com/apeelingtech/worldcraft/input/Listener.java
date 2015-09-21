package com.apeelingtech.worldcraft.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.apeelingtech.worldcraft.Game;

public class Listener implements MouseListener, MouseMotionListener, WindowListener, KeyListener, MouseWheelListener {
	
	private Game game;
	public boolean left = false, right = false, up = false, down = false, dragging = false;
	public int pressX = 0, pressY = 0, X = 0, Y = 0;
	
	public Listener(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			up = true;
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			down = true;
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			left = true;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			right = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			up = false;
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			down = false;
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			left = false;
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			right = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		game.paused = false;
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		game.running = false;
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		game.paused = true;
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		game.paused = false;
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		game.paused = true;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressX = e.getX();
		pressY = e.getY();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
		dragging = true;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}
