package state;

import gui.tree.DiagramView;
import gui.tree.DiagramView.Handle;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import model.tree.Diagram;
import app.MainFrame;

import commands.ResizeCommand;

public class ResizeState extends State {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8087278650910573098L;

	private Diagram med;
	Handle handleInMotion = null;
	Point startPosition = null;
	Dimension startDimension = null;
	Double scale;
	DiagramDevice device;

	public ResizeState(Diagram med) {
		this.med = med;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();

		Point position = e.getPoint();
		view.transformToUserSpace(position);
		if (handleInMotion == null) {
			handleInMotion = view.getDeviceAndHandleForPoint(position);
		}
		if (handleInMotion != null) {
			if (med.getSelectionModel().getSelectionListSize() == 1) {
				device = (DiagramDevice) med.getSelectionModel()
						.getElementFromSelectionListAt(0);
				if (startPosition == null || startDimension == null) {
					startPosition = (Point) device.getPosition().clone();
					startDimension = (Dimension) device.getSize().clone();
					scale = device.getScale();
				}

				Iterator<DiagramElement> it = med.getSelectionModel()
						.getSelectionListIterator();
				while (it.hasNext()) {
					DiagramElement element = it.next();
					if (element instanceof DiagramDevice) {
						// DiagramDevice device = (DiagramDevice) element;
						switch (handleInMotion.ordinal()) {
						// SouthEast
						case 4: {
							double razlikaX = position.getX()
									- (device.getPosition().getX() + device
											.getSize().getWidth());
							double razlikaY = position.getY()
									- (device.getPosition().getY() + device
											.getSize().getHeight());
							double newWidth = device.getSize().getWidth()
									+ razlikaX;
							double newHeight = device.getSize().getHeight()
									+ razlikaY;
							double scaleX = newWidth
									/ device.getInitialSize().getWidth();
							double scaleY = newHeight
									/ device.getInitialSize().getHeight();
							double newScale = 1;
							if (scaleX < scaleY)
								newScale = scaleX;
							else
								newScale = scaleY;
							if (newScale < 0.2)
								device.setScale(0.2);
							else if (newScale > 3)
								device.setScale(3);
							else
								device.setScale(newScale);
							break;
						}
						// SouthWest
						case 5: {
							// System.out.println(device.getPosition().getX());
							double oldWidth = device.getSize().getWidth();
							double razlikaX = device.getPosition().getX()
									- position.getX();
							double razlikaY = position.getY()
									- (device.getPosition().getY() + device
											.getSize().getHeight());
							double newWidth = device.getSize().getWidth()
									+ razlikaX;
							double newHeight = device.getSize().getHeight()
									+ razlikaY;
							double scaleX = newWidth
									/ device.getInitialSize().getWidth();
							double scaleY = newHeight
									/ device.getInitialSize().getHeight();
							double newScale = 1;
							if (scaleX < scaleY)
								newScale = scaleX;
							else
								newScale = scaleY;
							if (newScale < 0.2)
								device.setScale(0.2);
							else if (newScale > 3)
								device.setScale(3);
							else
								device.setScale(newScale);
							double newX = device.getPosition().getX()
									+ oldWidth - device.getSize().getWidth();
							device.setPosition(new Point((int) newX,
									(int) device.getPosition().getY()));
							break;
						}
						// NorthEast
						case 6: {
							double oldHeight = device.getSize().getHeight();
							double razlikaX = position.getX()
									- (device.getPosition().getX() + device
											.getSize().getWidth());
							double razlikaY = (device.getPosition().getY() + device
									.getSize().getHeight()) - position.getY();
							double newWidth = device.getSize().getWidth()
									+ razlikaX;
							double newHeight = device.getSize().getHeight()
									+ razlikaY;
							double scaleX = newWidth
									/ device.getInitialSize().getWidth();
							double scaleY = newHeight
									/ device.getInitialSize().getHeight();
							double newScale = 1;
							if (scaleX < scaleY)
								newScale = scaleX;
							else
								newScale = scaleY;
							if (newScale < 0.2)
								device.setScale(0.2);
							else if (newScale > 3)
								device.setScale(3);
							else
								device.setScale(newScale);
							double newY = device.getPosition().getY()
									+ oldHeight - device.getSize().getHeight();
							device.setPosition(new Point((int) device
									.getPosition().getX(), (int) newY));
							break;
						}
						// NorthWest
						case 7: {
							double oldHeight = device.getSize().getHeight();
							double oldWidth = device.getSize().getWidth();
							double razlikaX = device.getPosition().getX()
									- position.getX();
							// System.out.println(razlikaX + "x");
							double razlikaY = device.getPosition().getY()
									- position.getY();
							// System.out.println(razlikaY + "y");
							double newWidth = device.getSize().getWidth()
									+ razlikaX;
							double newHeight = device.getSize().getHeight()
									+ razlikaY;
							double scaleX = newWidth
									/ device.getInitialSize().getWidth();
							double scaleY = newHeight
									/ device.getInitialSize().getHeight();
							double newScale = 1;
							if (scaleX < scaleY)
								newScale = scaleX;
							else
								newScale = scaleY;
							if (newScale < 0.2)
								device.setScale(0.2);
							else if (newScale > 3)
								device.setScale(3);
							else
								device.setScale(newScale);
							double newY = device.getPosition().getY()
									+ oldHeight - device.getSize().getHeight();
							double newX = device.getPosition().getX()
									+ oldWidth - device.getSize().getWidth();
							device.setPosition(new Point((int) newX, (int) newY));
							break;
						}
						}
					}
					view.updatePerformed(null);
				}
			} else {
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();

		if (startDimension != null) {
			view.getCommandManager().addCommand(
					new ResizeCommand(device, view, startDimension,
							startPosition, scale));
		}

		handleInMotion = null;
		startDimension = null;
		startPosition = null;
		med.startSelectState();
	}

	@Override
	public String toString() {
		return "ResizeState";
	}
}
