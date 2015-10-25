package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.EventListenerList;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import event.UpdateEvent;
import event.UpdateListener;

public class DiagramModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3908391752836072029L;
	
	private static int count = 0;
	private String name;

	protected ArrayList<DiagramDevice> diagramElements = new ArrayList<DiagramDevice>();
	transient EventListenerList listenerList = new EventListenerList();
	UpdateEvent updateEvent = null;
	
	private Object readResolve(){
		listenerList = new EventListenerList();	
		return this;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		DiagramModel.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int getElementCount() {
		return diagramElements.size();
	}
	
	public ArrayList<DiagramDevice> getDiagramElements() {
		return diagramElements;
	}

	public Iterator<DiagramDevice> getDeviceIterator() {
		return diagramElements.iterator();
	}

	public void addDiagramElements(DiagramDevice device) {
		diagramElements.add(device);
		fireUpdatePerformed();
	}

	public void removeElement(DiagramElement element) {
		diagramElements.remove(element);
		fireUpdatePerformed();
	}

	public void addUpdateListener(UpdateListener l) {
		listenerList.add(UpdateListener.class, l);
	}

	public void removeUpdateListener(UpdateListener l) {
		listenerList.remove(UpdateListener.class, l);
	}

	public int getDeviceAtPosition(Point point) {
		for (int i = getDeviceCount() - 1; i >= 0; i--) {
			DiagramElement device = getDeviceAt(i);
			if (device.getPainter().elementAt(point)) {
				return i;
			}
		}
		return -1;
	}

	public DiagramElement getDeviceAt(int i) {
		return diagramElements.get(i);
	}

	public int getDeviceCount() {
		return diagramElements.size();
	}

	protected void fireUpdatePerformed() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 1; i >= 0; i -= 1) {
			if (listeners[i] == UpdateListener.class) {
				// Lazily create the event:
				if (updateEvent == null)
					updateEvent = new UpdateEvent(this);
				((UpdateListener) listeners[i + 1])
						.updatePerformed(updateEvent);

			}
		}
	}
}
