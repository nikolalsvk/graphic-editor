package state;

import gui.tree.DiagramView;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import model.tree.Diagram;
import app.MainFrame;

public class LassoState extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2673638843453063406L;

	Rectangle2D rect = new Rectangle2D.Double();

	private Diagram med;

	public LassoState(Diagram med) {
		this.med = med;
	}

	public void mouseDragged(MouseEvent e) {
		Point position = e.getPoint();
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();

		if (view != null) {
			view.transformToUserSpace(position);
		}

		if (!e.isControlDown()) {
			med.getSelectionModel().removeAllFromSelectionList();
		}

		double width = position.getX() - view.getLastPosition().getX();
		double height = position.getY() - view.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(position.getX(), position.getY(), Math.abs(width),
					Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(position.getX(), view.getLastPosition().getY(),
					Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(view.getLastPosition().getX(), position.getY(),
					Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(view.getLastPosition().getX(), view.getLastPosition()
					.getY(), Math.abs(width), Math.abs(height));
		}
		med.getSelectionModel().selectElements(rect,
				med.getModel().getDiagramElements());
		view.setSelectionRectangle(rect);

		view.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.setSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.repaint();
		med.startSelectState();
	}
	
	public String toString() {
		return "LassoState";
	}
}
