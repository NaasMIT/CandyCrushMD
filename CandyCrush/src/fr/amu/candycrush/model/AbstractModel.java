package fr.amu.candycrush.model;

import java.util.Collection;
import java.util.Observable;

import fr.amu.candycrush.core.Level;
import fr.amu.candycrush.core.SingletonGrid;

public class AbstractModel extends Observable {
	
	private SingletonGrid grid;
	private Collection<Level> setOfLevel;
	
	public SingletonGrid getGrid() {
		return grid;
	}
	public void setGrid(SingletonGrid grid) {
		this.grid = grid;
	}
	public Collection<Level> getSetOfLevel() {
		return setOfLevel;
	}
	public void setSetOfLevel(Collection<Level> setOfLevel) {
		this.setOfLevel = setOfLevel;
	}
}
