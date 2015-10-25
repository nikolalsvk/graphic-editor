package model.elements;

import gui.painters.HexagonPainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class HexagonElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6989368160121829379L;
	
	public HexagonElement(Point position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new HexagonPainter(this);
	}

	public static DiagramDevice createDefault(Point pos, int elemNo) {
		Point position = (Point) pos.clone();
		
		Paint fill = Color.WHITE;
		HexagonElement hexagonElement = new HexagonElement(position,
				new Dimension((int) ((int) 40*Math.sqrt(3)), 80), new BasicStroke((float) (2),
						BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL), fill,
				Color.BLACK);
		hexagonElement.setName("Hexagon " + elemNo);
		hexagonElement.setType("Hexagon");

		return hexagonElement;
	}

}
