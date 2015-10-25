package gui.painters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import model.elements.DiagramElement;

public abstract class ElementPainter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4360234009666986786L;
	
	protected Shape shape;
	protected DiagramElement element;
	public ElementPainter(DiagramElement element) {
		this.element=element;
	}

	public abstract void paint(Graphics2D g, DiagramElement element);

	public abstract boolean elementAt(Point pos);
	
	public Shape getShape() {
		return shape;
	}
}
