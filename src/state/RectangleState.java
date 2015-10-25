package state;

import gui.tree.DiagramView;

import java.awt.Point;
import java.awt.event.MouseEvent;

import model.tree.Diagram;
import app.MainFrame;

import commands.AddElementCommand;

public class RectangleState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7014782518011005658L;
	private Diagram med;

	public RectangleState(Diagram diagram) {
		med = diagram;
	}

	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		
		if (((DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame()) != null) {
			((DiagramView) MainFrame.getInstance().getDesktop()
					.getSelectedFrame()).transformToUserSpace(position);
		}
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (med.getModel().getDeviceAtPosition(position) == -1) {
				/*
				DiagramDevice device = RectangleElement.createDefault(position,
						med.getModel().getDeviceCount());
				med.getModel().addDiagramElements(device);
				*/
				
				((DiagramView) MainFrame.getInstance().getDesktop()
						.getSelectedFrame()).getCommandManager().addCommand(
						new AddElementCommand(med.getModel(), med.getSelectionModel(),
								position, this.toString()));
			}
		}
		
	}

	@Override
	public String toString() {
		return "RectangleState";
	}
}
