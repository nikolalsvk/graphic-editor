package model.elements;

import gui.painters.TrianglePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class TriangleElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2960038561765692185L;

	public TriangleElement(Point position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		// TODO Auto-generated constructor stub
		elementPainter = new TrianglePainter(this);
	}

	public static DiagramDevice createDefault(Point pos, int elemNo) {
		Point position = (Point) pos.clone();

		Paint fill = Color.WHITE;
		TriangleElement triangleElement = new TriangleElement(position,
				new Dimension(80, 100), new BasicStroke((float) (2),
						BasicStroke.JOIN_BEVEL, BasicStroke.JOIN_BEVEL), fill,
				Color.BLACK);
		triangleElement.setName("Triangle " + elemNo);
		triangleElement.setType("Triangle");
		
		return triangleElement;
	}
}
