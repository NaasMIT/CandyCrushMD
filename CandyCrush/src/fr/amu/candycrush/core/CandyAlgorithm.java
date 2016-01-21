package fr.amu.candycrush.core;

import java.awt.Color;
import java.util.Random;

public class CandyAlgorithm implements IAlgorithm {
		
	private Color colors[] = { Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.PINK, Color.CYAN };

	// est-ce qu'on a trois cases de la meme couleur vers le droite depuis (i,
	// j) ?
	public boolean horizontalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 6 || j >= 8)
			return false;
		if (SingletonGrid.getInstance().getCandyMatrix()[i][j] == SingletonGrid.getInstance().getCandyMatrix()[i + 1][j] && SingletonGrid.getInstance().getCandyMatrix()[i][j] == SingletonGrid.getInstance().getCandyMatrix()[i + 2][j])
			return true;
		return false;
	}

	// est-ce qu'on a trois cases de la meme couleur vers le bas depuis (i, j) ?
	public boolean verticalAligned(int i, int j) {
		if (i < 0 || j < 0 || i >= 8 || j >= 6)
			return false;
		if (SingletonGrid.getInstance().getCandyMatrix()[i][j] == SingletonGrid.getInstance().getCandyMatrix()[i][j + 1] && SingletonGrid.getInstance().getCandyMatrix()[i][j] == SingletonGrid.getInstance().getCandyMatrix()[i][j + 2])
			return true;
		return false;
	}

	// echanger le contenu de deux cases
	public void swap(int x1, int y1, int x2, int y2) {
		Candy tmp = SingletonGrid.getInstance().getCandyMatrix()[x1][y1];
		SingletonGrid.getInstance().getCandyMatrix()[x1][y1] = SingletonGrid.getInstance().getCandyMatrix()[x2][y2];
		SingletonGrid.getInstance().getCandyMatrix()[x2][y2] = tmp;
	}

	// determine si l'echange entre deux cases est valide
	public boolean isValidSwap(int x1, int y1, int x2, int y2) {
		// il faut que les cases soient dans la grille
		if (x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1)
			return false;
		// que les cases soient e cete l'une de l'autre
		if (Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1)
			return false;
		// et que les couleurs soient differentes
		if (SingletonGrid.getInstance().getCandyMatrix()[x1][y1] == SingletonGrid.getInstance().getCandyMatrix()[x2][y2])
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
	public boolean removeAlignments() {
		// passe 1 : marquer tous les alignements
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Candy currentCandy = SingletonGrid.getInstance().getCandyMatrix()[i][j];
				if (currentCandy != null && horizontalAligned(i, j)) {
					SingletonGrid.getInstance().getCandyMatrix()[i][j].setMarked(true);
					SingletonGrid.getInstance().getCandyMatrix()[i+1][j].setMarked(true);
					SingletonGrid.getInstance().getCandyMatrix()[i+2][j].setMarked(true);
				}
				if (SingletonGrid.getInstance().getCandyMatrix()[i][j] != null && verticalAligned(i, j)) {
					SingletonGrid.getInstance().getCandyMatrix()[i][j].setMarked(true);
					SingletonGrid.getInstance().getCandyMatrix()[i][j+1].setMarked(true);
					SingletonGrid.getInstance().getCandyMatrix()[i][j+2].setMarked(true);
				}
			}
		}
		// passe 2 : supprimer les cases marquees
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (SingletonGrid.getInstance().getCandyMatrix()[i][j].isMarked()) {
					SingletonGrid.getInstance().getCandyMatrix()[i][j] = null;
					SingletonGrid.getInstance().getCandyMatrix()[i][j].setMarked(false);
					modified = true;
				}
			}
		}
		return modified;
	}

	// remplir les cases vides par gravite, et generer des cases aleatoirement
	// par le haut
	public boolean fill() {
		Random rand = new Random();
		boolean modified = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >= 0; j--) {
				if (SingletonGrid.getInstance().getCandyMatrix()[i][j] == null) {
					if (j == 0)
					{
						Candy c = new Candy(colors[1 + rand.nextInt(colors.length - 1)]);
						SingletonGrid.getInstance().getCandyMatrix()[i][j] = c;
					} else {
						SingletonGrid.getInstance().getCandyMatrix()[i][j] = SingletonGrid.getInstance().getCandyMatrix()[i][j - 1];
						SingletonGrid.getInstance().getCandyMatrix()[i][j - 1] = null;
					}
					modified = true;
				}
			}
		}
		return modified;
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// remplir une premiere fois la grille
		while (fill())
			;
		// enlever les alignements existants
		while (removeAlignments()) {
			fill();
		}		
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		
	}

}
