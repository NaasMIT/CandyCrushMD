package fr.amu.candycrush.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fr.amu.candycrush.model.ModelMainFrame;
import fr.amu.candycrush.view.ViewMainFrame;

public class ControllerMainFrame implements ActionListener, MouseMotionListener, MouseListener {

	private ModelMainFrame modelMainFrame;
	private ViewMainFrame viewMainFrame;
	
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
