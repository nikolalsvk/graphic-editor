package gui.painters;

import java.awt.geom.GeneralPath;

import model.elements.DiagramElement;
import model.elements.TriangleElement;

public class TrianglePainter extends DevicePainter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3726503587145755820L;

	public TrianglePainter(DiagramElement element) {
		super(element);
		// TODO Auto-generated constructor stub
		TriangleElement triangle = (TriangleElement) element;
		
		shape = new GeneralPath();
		((GeneralPath)shape).moveTo(0, 0);
		
		((GeneralPath)shape).lineTo(triangle.getSize().width, 0);
		
		((GeneralPath)shape).lineTo(triangle.getSize().width/2, triangle.getSize().height);
		
		((GeneralPath)shape).closePath();
	}

}
