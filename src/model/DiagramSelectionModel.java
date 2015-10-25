package model;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.event.EventListenerList;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import event.UpdateEvent;
import event.UpdateListener;

public class DiagramSelectionModel extends DefaultSingleSelectionModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158568020985234811L;

	private ArrayList<DiagramElement> selectionList = new ArrayList<DiagramElement>();
	transient EventListenerList listenerList = new EventListenerList();
	UpdateEvent updateEvent = null;

	public void addToSelectionList(DiagramElement element) {
		selectionList.add(element);
		fireUpdatePerformed();
	}

	public void addToSelectionList(ArrayList<DiagramElement> list) {
		selectionList.addAll(list);
		fireUpdatePerformed();
	}

	public int getSelectionListSize() {
		return selectionList.size();
	}

	public DiagramElement getElementFromSelectionListAt(int index) {
		return (DiagramElement) selectionList.get(index);
	}

	public int getIndexByObject(DiagramElement element) {
		return selectionList.indexOf(element);
	}

	public void removeFromSelectionList(DiagramElement element) {
		selectionList.remove(element);
		fireUpdatePerformed();
	}

	public void removeAllFromSelectionList() {
		selectionList.clear();
		fireUpdatePerformed();
	}

	public ArrayList<DiagramElement> getSelectionList() {
		return selectionList;
	}

	public Iterator<DiagramElement> getSelectionListIterator() {
		return selectionList.iterator();
	}

	public boolean isElementSelected(DiagramElement element) {
		return selectionList.contains(element);

	}

	public void selectElements(Rectangle2D rec,
			ArrayList<DiagramDevice> elements) {
		Iterator<DiagramDevice> it = elements.iterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				if (rec.intersects(device.getPosition().getX(), device
						.getPosition().getY(), device.getSize().getWidth(),
						device.getSize().getHeight())) {
					if (!isElementSelected(device))
						selectionList.add(device);
				}
			}
		}
	}

	public void addUpdateListener(UpdateListener l) {
		if(listenerList == null) {
			listenerList = new EventListenerList();
		}
		listenerList.add(UpdateListener.class, l);
	}

	public void removeUpdateListener(UpdateListener l) {
		listenerList.remove(UpdateListener.class, l);
	}

	public void fireUpdatePerformed() {
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
