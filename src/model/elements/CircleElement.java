package model.elements;

import gui.painters.CirclePainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class CircleElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110348673740970658L;

	public CircleElement(Point position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new CirclePainter(this);
	}

	public static DiagramDevice createDefault(Point pos, int elemNo) {
		Point position = (Point) pos.clone();
		
		Paint fill = Color.WHITE;
		CircleElement circleElement = new CircleElement(position,
				new Dimension(80, 80), new BasicStroke((float) (2),
						BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL), fill,
				Color.BLACK);
		circleElement.setName("Circle " + elemNo);
		circleElement.setType("Circle");

		return circleElement;
	}

}
