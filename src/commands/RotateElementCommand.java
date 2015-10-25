package commands;

import gui.tree.DiagramView;

import java.util.ArrayList;

import model.DiagramSelectionModel;
import model.elements.DiagramDevice;
import model.elements.DiagramElement;

public class RotateElementCommand extends AbstractCommand {

	private DiagramView frame;
	private ArrayList<DiagramElement> selectedElements;
	private String direction;

	@SuppressWarnings("unchecked")
	public RotateElementCommand(DiagramView frame,
			DiagramSelectionModel selectionModel, String direction) {
		super();
		this.frame = frame;
		this.selectedElements = (ArrayList<DiagramElement>) selectionModel
				.getSelectionList().clone();
		this.direction = direction;
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		
		if (direction == "CW") {
			for (DiagramElement element : selectedElements) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() - Math.PI / 2);
				
				device.setRotated(true);
				/*
				device.setRotationActive(true);
				device.setSize(new Dimension(
						(int) device.getSize().getHeight(), (int) device
								.getSize().getWidth()));
				*/		
				//device.setPosition(new Point((int) (device.getPosition() + device.getSize().getHeight(), 0));
			}
		} else {
			for (DiagramElement element : selectedElements) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() + Math.PI / 2);
				
				device.setRotated(true);
				/*
				device.setRotationActive(true);
				device.setSize(new Dimension(
						(int) device.getSize().getHeight(), (int) device
								.getSize().getWidth()));
				*/
			}
		}
		frame.updatePerformed(null);
		
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		if (direction == "CW") {
			for (DiagramElement element : selectedElements) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() + Math.PI / 2);	
				device.setRotated(true);
			}
		} else {
			for (DiagramElement element : selectedElements) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() - Math.PI / 2);
				device.setRotated(true);
			}
		}
		frame.updatePerformed(null);
	}

}
