package fr.amu.candycrush.model;

import java.util.Observable;

import fr.amu.candycrush.core.CandyAlgorithm;
import fr.amu.candycrush.core.IAlgorithm;
import fr.amu.candycrush.core.SingletonGrid;

public class ModelMainFrame extends Observable {

	private IAlgorithm algo;

	public ModelMainFrame(IAlgorithm algo) {
		SingletonGrid.getInstance();
		this.algo = algo;
		init();

	}

	public void init() {
		algo.init();
	}

	public boolean fill() {
		boolean result = ((CandyAlgorithm)algo).fill();

		notifyObservers(SingletonGrid.getInstance());

		return result;
	}

	public void swap(int x, int y, int z, int o) {
		((CandyAlgorithm) algo).swap(x, y, z, o);

		notifyObservers(SingletonGrid.getInstance());
	}

	// determine si l'echange entre deux cases est valide
	public boolean isValidSwap(int x1, int y1, int x2, int y2) {
		boolean isValid = ((CandyAlgorithm) algo).isValidSwap(x1, y1, x2, y2);

		notifyObservers(SingletonGrid.getInstance());

		return isValid;
	}

	public boolean removeAlignments() {
		boolean isRemoved = ((CandyAlgorithm) algo).removeAlignments();

		notifyObservers(SingletonGrid.getInstance());

		return isRemoved;
	}

}
