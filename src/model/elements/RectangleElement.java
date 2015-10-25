package model.elements;

import gui.painters.RectanglePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class RectangleElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6239903262602243613L;

	public RectangleElement(Point position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new RectanglePainter(this);
	}

	public static DiagramDevice createDefault(Point pos, int elemNo) {
		Point position = (Point) pos.clone();

		Paint fill = Color.WHITE;
		RectangleElement rectangleElement = new RectangleElement(position,
				new Dimension(100, 60), new BasicStroke((float) (2),
						BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL), fill,
				Color.BLACK);
		rectangleElement.setName("Rectangle " + elemNo);
		rectangleElement.setType("Rectangle");

		return rectangleElement;
	}
}
