package model.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class DiagramDevice extends DiagramElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4116258677521095503L;
	protected Dimension size;
	protected Dimension initialSize;
	protected Point position;
	protected Point initialPosition;
	protected double scale;
	protected double rotation;
	protected boolean rotated;
	protected boolean rotationActive;

	public DiagramDevice(Point position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(stroke, paint, strokeColor);
		this.size = size;
		this.initialSize = size;
		position.setLocation(position.getX()-size.getWidth()/2,position.getY()-size.getHeight()/2);
		this.position = position;
		this.initialPosition = position;
		this.scale = 1;
		this.rotation = 0;
		this.rotationActive = false;
	}

	public Dimension getInitialSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(Point initialPosition) {
		this.initialPosition = initialPosition;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public Dimension getSize() {
		Dimension tempSize = new Dimension();
		tempSize.setSize(size.getWidth() * getScale(), size.getHeight()
				* getScale());
		return tempSize;
	}

	public boolean isRotated() {
		return rotated;
	}

	public void setRotated(boolean rotated) {
		if(this.rotated) {
			this.rotated = false;
		} else {
			this.rotated = true;
		}
	}

	public void setRotationActive(boolean b) {
		// TODO Auto-generated method stub
		this.rotationActive = b;
	}
	
	public boolean getRotationActive() {
		return rotationActive;
	}

}
