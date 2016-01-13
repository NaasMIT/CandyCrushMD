package fr.amu.candycrush.core;

public final class SingletonGrid {

	private static SingletonGrid singletonGrid;
	private Candy [][] candyMatrix;


	private SingletonGrid() {
		setCandyMatrix(new Candy [8][8]);
	}

	private static SingletonGrid getInstance() {
		if (singletonGrid == null) singletonGrid = new SingletonGrid();

		return singletonGrid;
	}

	public Candy [][] getCandyMatrix() {
		return candyMatrix;
	}

	public void setCandyMatrix(Candy [][] candyMatrix) {
		this.candyMatrix = candyMatrix;
	}
}
