package fr.amu.candycrush.core;

import java.awt.Color;

public class Candy {
	private boolean isMarked = false;
	private Color color;
	
	public Candy (Color color) {
		this.setColor(color);
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
