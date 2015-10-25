package gui.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;

public class DevicePainter extends ElementPainter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4107577218766966121L;

	public DevicePainter(DiagramElement element) {
		super(element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics2D g, DiagramElement element) {
		// TODO Auto-generated method stub
		AffineTransform oldTransform = g.getTransform();
		DiagramDevice device = (DiagramDevice) element;

		g.translate((int) device.getPosition().getX(), (int) device
				.getPosition().getY());
		/*
		 * if (device.isRotated()) { g.rotate(device.getRotation(),
		 * device.getInitialSize().getHeight() / 2,
		 * device.getInitialSize().getWidth() / 2);
		 * 
		 * int xPoint = (int) (device.getPosition().getX() -
		 * (device.getInitialSize() .getWidth() -
		 * device.getInitialSize().getHeight()) / 2); int yPoint = (int)
		 * (device.getPosition().getY() + (device.getInitialSize() .getWidth() -
		 * device.getInitialSize().getHeight()) / 2);
		 * if(device.getRotationActive()) { device.setPosition(new Point(xPoint,
		 * yPoint)); System.out.println("Test");
		 * device.setRotationActive(false); } } else {
		 * g.rotate(device.getRotation(), device.getInitialSize().getWidth() /
		 * 2, device.getInitialSize().getHeight() / 2);
		 * if(device.getRotationActive()) {
		 * device.setPosition(device.getInitialPosition());
		 * System.out.println("Test2"); device.setRotationActive(false); } }
		 */
		g.rotate(device.getRotation(), device.getSize().getWidth() / 2, device
				.getSize().getHeight() / 2);
		g.scale(device.getScale(), device.getScale());

		g.setPaint(Color.CYAN);
		g.setStroke(element.getStroke());
		g.draw(getShape());

		g.setPaint(element.getPaint());
		g.fill(getShape());

		g.setPaint(Color.BLACK);
		g.drawString(device.getName(), 2,
				(int) device.getSize().getHeight() / 2);
		g.setTransform(oldTransform);
	}

	@Override
	public boolean elementAt(Point pos) {
		// TODO Auto-generated method stub
		DiagramDevice device = (DiagramDevice) element;
		Rectangle2D rect = new Rectangle2D.Double();
		if (device.isRotated()) {
			// rotated position
			int xPoint = (int) (device.getPosition().getX() - (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);
			int yPoint = (int) (device.getPosition().getY() + (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);
			
			rect.setRect(xPoint, yPoint, device.getSize().getHeight(), device.getSize()
					.getWidth());
		} else {
			rect.setRect(device.getPosition().getX(), device.getPosition()
					.getY(), device.getSize().getWidth(), device.getSize()
					.getHeight());
		}
		return rect.contains(pos);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
