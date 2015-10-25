package gui.painters;

import java.awt.geom.Ellipse2D;

import model.elements.CircleElement;
import model.elements.DiagramElement;

public class CirclePainter extends DevicePainter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3683105905990340803L;

	public CirclePainter(DiagramElement element) {
		super(element);
		// TODO Auto-generated constructor stub

		CircleElement circle = (CircleElement) element;
		
		shape = new Ellipse2D.Double(0, 0, circle.getSize().getHeight(), circle
				.getSize().getHeight());
				
	}

}
