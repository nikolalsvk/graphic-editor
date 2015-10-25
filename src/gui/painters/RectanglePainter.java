package gui.painters;

import java.awt.geom.GeneralPath;

import model.elements.DiagramElement;
import model.elements.RectangleElement;

public class RectanglePainter extends DevicePainter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5075292844601441716L;

	public RectanglePainter(DiagramElement element) {
		super(element);
		// TODO Auto-generated constructor stub

		RectangleElement rectangle = (RectangleElement) element;

		shape = new GeneralPath();
		//System.out.println(rectangle.getPosition() + "Test");
		((GeneralPath) shape).moveTo(0, 0);

		((GeneralPath) shape).lineTo(0 + rectangle.getSize().width, 0);

		((GeneralPath) shape).lineTo(0 + rectangle.getSize().width,
				0 + rectangle.getSize().height);

		((GeneralPath) shape).lineTo(0, 0 + rectangle.getSize().height);

		((GeneralPath) shape).closePath();
	}

}
