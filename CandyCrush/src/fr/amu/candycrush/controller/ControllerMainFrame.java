package fr.amu.candycrush.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fr.amu.candycrush.core.CandyAlgorithm;
import fr.amu.candycrush.core.IAlgorithm;
import fr.amu.candycrush.core.SingletonGrid;
import fr.amu.candycrush.model.ModelMainFrame;
import fr.amu.candycrush.view.ViewMainFrame;

public class ControllerMainFrame implements ActionListener, MouseMotionListener, MouseListener, Runnable {

	private ModelMainFrame modelMainFrame;
	private ViewMainFrame viewMainFrame;

	public ControllerMainFrame(ModelMainFrame modelMainFrame, ViewMainFrame viewMainFrame) {
		this.modelMainFrame = modelMainFrame;
		this.viewMainFrame = viewMainFrame;

		IAlgorithm candyAlgo = new CandyAlgorithm();

		listenViewComponent();

		modelMainFrame.addObserver(viewMainFrame);
		
		modelMainFrame.notifyObs(SingletonGrid.getInstance());
		new Thread(this).start();
	}

	public ControllerMainFrame() {
		IAlgorithm candyAlgo = new CandyAlgorithm();

		modelMainFrame = new ModelMainFrame(candyAlgo);
		viewMainFrame = new ViewMainFrame();

		modelMainFrame.notifyObservers(SingletonGrid.getInstance());

		listenViewComponent();

		modelMainFrame.addObserver(viewMainFrame);
		modelMainFrame.notifyObs(SingletonGrid.getInstance());
		new Thread(this).start();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		// on bouge la souris : recuperer les coordonnees de la deuxieme case
		System.err.println(viewMainFrame.getGamePanel().getSelectedX());
		if (viewMainFrame.getGamePanel().getSelectedX() != -1 && 
				viewMainFrame.getGamePanel().getSelectedY() != -1) {

			System.out.println("getSeconde case");
			viewMainFrame.getGamePanel().setSelectedX(e.getX() / 32);
			viewMainFrame.getGamePanel().setSelectedY(e.getY() / 32);

			// si l'echange n'est pas valide, on cache la deuxieme case
			if (!modelMainFrame.isValidSwap(viewMainFrame.getGamePanel().getSelectedX(), 
					viewMainFrame.getGamePanel().getSelectedY(), 
					viewMainFrame.getGamePanel().getSwappedX(), 
					viewMainFrame.getGamePanel().getSwappedY())) {

				viewMainFrame.getGamePanel().setSwappedX(-1);
				viewMainFrame.getGamePanel().setSwappedY(-1);
			}

			System.out.println(modelMainFrame.countObservers());
			modelMainFrame.notifyObs(SingletonGrid.getInstance());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// on appuie sur le bouton de la souris : recuperer les coordonnees de
		// la premiere case
		if(e.getSource() == viewMainFrame.getGamePanel())
		{
			System.out.println(e.getPoint());
			viewMainFrame.getGamePanel().setSelectedX(e.getX() / 32);
			viewMainFrame.getGamePanel().setSelectedY(e.getY() / 32);

			modelMainFrame.notifyObs(SingletonGrid.getInstance());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// lorsque l'on releche la souris il faut faire l'echange et cacher les
		// cases
		if(e.getSource() == viewMainFrame.getGamePanel())
		{
			if (viewMainFrame.getGamePanel().getSelectedX() != -1 && 
					viewMainFrame.getGamePanel().getSelectedY() != -1 && 
					viewMainFrame.getGamePanel().getSwappedX() != -1 && 
					viewMainFrame.getGamePanel().getSwappedY() != -1) {

				modelMainFrame.swap(viewMainFrame.getGamePanel().getSelectedX(), 
						viewMainFrame.getGamePanel().getSelectedY(), 
						viewMainFrame.getGamePanel().getSwappedX(), 
						viewMainFrame.getGamePanel().getSwappedY());
			}

			viewMainFrame.getGamePanel().setSelectedX(-1);
			viewMainFrame.getGamePanel().setSelectedY(-1);
			viewMainFrame.getGamePanel().setSwappedX(-1);
			viewMainFrame.getGamePanel().setSwappedY(-1);

			modelMainFrame.notifyObs(SingletonGrid.getInstance());
		}
	}

	public ModelMainFrame getModelMainFrame() {
		return modelMainFrame;
	}

	public void setModelMainFrame(ModelMainFrame modelMainFrame) {
		this.modelMainFrame = modelMainFrame;
	}

	public ViewMainFrame getViewMainFrame() {
		return viewMainFrame;
	}

	public void setViewMainFrame(ViewMainFrame viewMainFrame) {
		this.viewMainFrame = viewMainFrame;
	}

	private void listenViewComponent() {
		viewMainFrame.getGamePanel().addMouseListener((MouseListener) this);
		viewMainFrame.getGamePanel().addMouseMotionListener((MouseMotionListener) this);
	}

	@Override
	public void run() {
		while (true) {
			// un pas de simulation toutes les 100ms
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
			}

			// s'il n'y a pas de case vide, chercher des alignements
			if (!modelMainFrame.fill()) {
				modelMainFrame.removeAlignments();
			}

			modelMainFrame.notifyObs(SingletonGrid.getInstance());
		}

	}
}
