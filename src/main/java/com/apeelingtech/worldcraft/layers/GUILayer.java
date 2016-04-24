package com.apeelingtech.worldcraft.layers;

import java.awt.Graphics;

import com.apeelingtech.worldcraft.Game;
import com.apeelingtech.worldcraft.events.Event;
import com.apeelingtech.worldcraft.graphics.Sprite;

public class GUILayer extends Layer {

	@Override
	public void onEvent(Event event) {

	}

	@Override
	public void onUpdate() {

	}

	@Override
	public void onRender(Graphics g) {
		Sprite.invCell.draw(g, Game.SIZE.width / 2, Game.SIZE.height - Sprite.invCell.getHeight() - 10);
	}

}
