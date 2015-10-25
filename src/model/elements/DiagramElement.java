package model.elements;

import gui.painters.ElementPainter;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import serialization.SerializableStrokeAdapter;

public class DiagramElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1785187813502482395L;
	
	protected Paint paint;
	protected SerializableStrokeAdapter stroke;
	protected Color strokeColor;
	
	protected String name;
	protected String description;
	protected String type;
	
	protected ElementPainter elementPainter;
	
	public DiagramElement(Stroke stroke, Paint paint, Color strokeColor){
		setStroke(stroke);
		this.paint = paint;
		this.strokeColor = strokeColor;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ElementPainter getPainter() {
		return elementPainter;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

}
