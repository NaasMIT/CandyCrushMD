package fr.amu.candycrush.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;

import fr.amu.candycrush.core.SingletonGrid;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;
	// coordonnees des cases selectionnees : -1 = non selectionne
	private int selectedX = -1, selectedY = -1;
	private int swappedX = -1, swappedY = -1;
	
	//image pour le rendu hors ecran
	private Image buffer;
	private Graphics g;
	
	private Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };

	// evite le scintillement
	public void update(Graphics g) {
		paint(g);
	}

	// routine d'affichage : on fait du double buffering
	public void paint(Graphics g2) {
		if (buffer == null)
			buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		// fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// afficher la grille vide
		g.setColor(Color.BLACK);
		for (int i = 0; i < 9; i++) {
			g.drawLine(32 * i, 0, 32 * i, 8 * 32 + 1);
			g.drawLine(0, 32 * i, 8 * 32 + 1, 32 * i);
		}

		// afficher la premiere case selectionnee
		if (selectedX != -1 && selectedY != -1) {
			g.setColor(Color.ORANGE);
			g.fillRect(selectedX * 32 + 1, selectedY * 32 + 1, 31, 31);
		}

		// afficher la deuxieme case selectionnee
		if (swappedX != -1 && swappedY != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(swappedX * 32 + 1, swappedY * 32 + 1, 31, 31);
		}

		// afficher le contenu de la grille
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.setColor(SingletonGrid.getInstance().getCandyMatrix()[i][j].getColor());
				g.fillOval(32 * i + 3, 32 * j + 3, 27, 27);
			}
		}

		// copier l'image e l'ecran
		g2.drawImage(buffer, 0, 0, null);
	}
	
	// taille de la fenetre
	public Dimension getPreferredSize() {
		return new Dimension(32 * 8 + 1, 32 * 8 + 1);
	}

	public int getSelectedX() {
		return selectedX;
	}

	public void setSelectedX(int selectedX) {
		this.selectedX = selectedX;
	}

	public int getSelectedY() {
		return selectedY;
	}

	public void setSelectedY(int selectedY) {
		this.selectedY = selectedY;
	}

	public int getSwappedX() {
		return swappedX;
	}

	public void setSwappedX(int swappedX) {
		this.swappedX = swappedX;
	}

	public int getSwappedY() {
		return swappedY;
	}

	public void setSwappedY(int swappedY) {
		this.swappedY = swappedY;
	}
}
