package commands;

import gui.tree.DiagramView;

import java.awt.Dimension;
import java.awt.Point;

import action.command.RedoAction;

import model.elements.DiagramDevice;

public class ResizeCommand extends AbstractCommand {
	DiagramDevice device;
	Point position;
	Point startPosition;
	Dimension startDimension;
	Double startScale;
	Point endPosition;
	Dimension endDimension;
	Double endScale;
	
	int brojac = 0; // brojac koji utvrdjuje po koji put se ulazi u doCommand()

	DiagramView view;

	public ResizeCommand(DiagramDevice device, DiagramView view,
			Dimension startDimension, Point startPosition, Double scale) {
		this.device = device;
		this.view = view;
		this.startScale = scale;
		this.startDimension = startDimension;
		this.startPosition = startPosition;
		this.endPosition = device.getPosition();
		this.endDimension = device.getSize();
		this.endScale = device.getScale();
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		brojac++;
		if (brojac != 1) {
			device.setPosition(endPosition);
			device.setScale(endScale);
			RedoAction.setRedoClicked(false);
			view.updatePerformed(null);
		}
		
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		//device.setSize(startDimension);
		device.setPosition(startPosition);
		device.setScale(startScale);
		view.updatePerformed(null);
	}

}
