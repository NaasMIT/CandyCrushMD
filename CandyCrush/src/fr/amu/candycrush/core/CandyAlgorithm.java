package fr.amu.candycrush.core;

import java.awt.Color;
import java.util.Random;

public class CandyAlgorithm {
	
	private SingletonGrid singletonGrid;
	
	private Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };
	
	public SingletonGrid getSingletonGrid() {
		return singletonGrid;
	}
	
	public void setSingletonGrid(SingletonGrid singletonGrid) {
		this.singletonGrid = singletonGrid;
	}
	
	
	// est-ce qu'on a trois cases de la meme couleur vers le droite depuis (i,
	// j) ?
	boolean horizontalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 6 || j >= 8)
			return false;
		if (singletonGrid.getCandyMatrix()[i][j] == singletonGrid.getCandyMatrix()[i + 1][j] && singletonGrid.getCandyMatrix()[i][j] == singletonGrid.getCandyMatrix()[i + 2][j])
			return true;
		return false;
	}

	// est-ce qu'on a trois cases de la meme couleur vers le bas depuis (i, j) ?
	boolean verticalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if (singletonGrid.getCandyMatrix()[i][j] == singletonGrid.getCandyMatrix()[i][j + 1] && singletonGrid.getCandyMatrix()[i][j] == singletonGrid.getCandyMatrix()[i][j + 2])
			return true;
		return false;
	}

	// echanger le contenu de deux cases
	void swap(int x1, int y1, int x2, int y2) {
		Candy tmp = singletonGrid.getCandyMatrix()[x1][y1];
		singletonGrid.getCandyMatrix()[x1][y1] = singletonGrid.getCandyMatrix()[x2][y2];
		singletonGrid.getCandyMatrix()[x2][y2] = tmp;
	}

	// determine si l'echange entre deux cases est valide
	boolean isValidSwap(int x1, int y1, int x2, int y2) {
		// il faut que les cases soient dans la grille
		if (x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1)
			return false;
		// que les cases soient e cete l'une de l'autre
		if (Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1)
			return false;
		// et que les couleurs soient differentes
		if (singletonGrid.getCandyMatrix()[x1][y1] == singletonGrid.getCandyMatrix()[x2][y2])
			return false;

		// alors on effectue l'echange
		swap(x1, y1, x2, y2);

		// et on verifie que ea cree un nouvel alignement
		boolean newAlignment = false;
		for (int i = 0; i < 3; i++) {
			newAlignment |= horizontalAligned(x1 - i, y1);
			newAlignment |= horizontalAligned(x2 - i, y2);
			newAlignment |= verticalAligned(x1, y1 - i);
			newAlignment |= verticalAligned(x2, y2 - i);
		}

		// puis on annule l'echange
		swap(x1, y1, x2, y2);
		return newAlignment;
	}

	// supprimer les alignements
	boolean removeAlignments() {
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Candy currentCandy = singletonGrid.getCandyMatrix()[i][j];
				if (currentCandy != null && horizontalAligned(i, j)) {
					singletonGrid.getCandyMatrix()[i][j].setMarked(true);
					singletonGrid.getCandyMatrix()[i+1][j].setMarked(true);
					singletonGrid.getCandyMatrix()[i+2][j].setMarked(true);
				}
				if (singletonGrid.getCandyMatrix()[i][j] != null && verticalAligned(i, j)) {
					singletonGrid.getCandyMatrix()[i][j].setMarked(true);
					singletonGrid.getCandyMatrix()[i][j+1].setMarked(true);
					singletonGrid.getCandyMatrix()[i][j+2].setMarked(true);
				}
			}
		}
		// passe 2 : supprimer les cases marquees
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (singletonGrid.getCandyMatrix()[i][j].isMarked()) {
					singletonGrid.getCandyMatrix()[i][j] = null;
					singletonGrid.getCandyMatrix()[i][j].setMarked(false);
					modified = true;
				}
			}
		}
		return modified;
	}

	// remplir les cases vides par gravite, et generer des cases aleatoirement
	// par le haut
	boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >= 0; j--) {
				if (singletonGrid.getCandyMatrix()[i][j] == null) {
					if (j == 0)
					{
						Candy c = new Candy(colors[1 + rand.nextInt(colors.length - 1)]);
						singletonGrid.getCandyMatrix()[i][j] = c;
					} else {
						singletonGrid.getCandyMatrix()[i][j] = singletonGrid.getCandyMatrix()[i][j - 1];
						singletonGrid.getCandyMatrix()[i][j - 1] = null;
					}
					modified = true;
				}
			}
		}
		return modified;
	}

}
