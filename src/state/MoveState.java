package state;

import gui.tree.DiagramView;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import model.tree.Diagram;
import app.MainFrame;

public class MoveState extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4377421125766509639L;

	private Diagram med;

	public MoveState(Diagram med) {
		this.med = med;
	}

	public void mouseDragged(MouseEvent e) {
		MainFrame.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();

		Point lastPosition = e.getPoint();
		// System.out.println(lastPosition + " start");
		// proba za getDiagram() u klasi Diagram
		// propalo
		view.transformToUserSpace(lastPosition);

		double xx = view.getLastPosition().getX() - lastPosition.getX();
		double yy = view.getLastPosition().getY() - lastPosition.getY();

		Iterator<DiagramElement> it = med.getSelectionModel()
				.getSelectionListIterator();

		while (it.hasNext()) {
			DiagramElement element = it.next();
			DiagramDevice device = (DiagramDevice) element;
			Point newPosition = (Point) device.getPosition().clone();
			// System.out.println(newPosition + " middle");
			newPosition.setLocation(newPosition.getX() - xx, newPosition.getY()
					- yy);

			device.setPosition(newPosition);
			// device.getPainter().paint(g2, element)
		}

		view.setLastPosition(lastPosition);
		view.updatePerformed(null);

	}

	public void mouseReleased(MouseEvent e) {
		MainFrame.getInstance().setCursor(Cursor.getDefaultCursor());

		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();
		Point lastPosition = e.getPoint();

		view.transformToUserSpace(lastPosition);
		view.setLastPosition(lastPosition);
		view.repaint();
		med.startSelectState();
	}

	@Override
	public String toString() {
		return "MoveState";
	}

}
