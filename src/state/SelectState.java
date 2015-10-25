package state;

import gui.tree.DiagramView;
import gui.tree.DiagramView.Handle;

import java.awt.Point;
import java.awt.event.MouseEvent;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import model.tree.Diagram;
import app.MainFrame;
import app.tools.ElementChangeDialog;

public class SelectState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7941040402900119340L;
	private Diagram med;
	private int elementInMotion = -1;
	private Handle handleInMotion = null;
	private int mouseButton = 0;

	public SelectState(Diagram diagram) {
		med = diagram;
	}

	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		mouseButton = e.getButton();

		if (((DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame()) != null) {
			((DiagramView) MainFrame.getInstance().getDesktop()
					.getSelectedFrame()).transformToUserSpace(position);
		}

		if (e.getButton() == MouseEvent.BUTTON1) {
			handleInMotion = ((DiagramView) MainFrame.getInstance()
					.getDesktop().getSelectedFrame())
					.getDeviceAndHandleForPoint(position);

			if (handleInMotion == null) {
				if (!e.isControlDown()) {
					med.getSelectionModel().removeAllFromSelectionList();
				}
				elementInMotion = med.getModel().getDeviceAtPosition(position);
				if (elementInMotion != -1) {
					// pogodjen je element, ukoliko je selektovan treba ga
					// ukloniti iz liste,
					// ako nije treba ga dodati u listu

					DiagramElement element = med.getModel().getDeviceAt(
							elementInMotion);
					DiagramDevice device = (DiagramDevice) element;

					if (med.getSelectionModel().isElementSelected(element)) {
						med.getSelectionModel()
								.removeFromSelectionList(element);
					} else {
						med.getSelectionModel().addToSelectionList(element);
						if (med.getSelectionModel().getSelectionListSize() == 1) {
							MainFrame.getInstance().getStatusbar()
									.setElementName(element.getName());
							MainFrame
									.getInstance()
									.getStatusbar()
									.setDimension(
											device.getSize().width + " * "
													+ device.getSize().height);
							MainFrame.getInstance().getStatusbar()
									.setElementType(element.getType());
						} else {
							MainFrame.getInstance().getStatusbar()
									.resetStatusBar();
						}
					}

				} else {
					// nije pogodjen nijedan element
					MainFrame.getInstance().getStatusbar().resetStatusBar();
				}
			} else {
				// pogodjen je handl nad nekim elementom, potrebno je raditi
				// resize elementa
				med.startResizeState();
			}
		}

		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2
				&& !e.isControlDown()) {
			if (med.getSelectionModel().getSelectionList().size() == 1) {
				ElementChangeDialog ed = new ElementChangeDialog(med, med
						.getSelectionModel().getSelectionList().get(0),
						(DiagramView) MainFrame.getInstance().getDesktop()
								.getSelectedFrame());
				ed.setVisible(true);
				System.out.println("Dijalog");
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		Point position = e.getPoint();
		if (((DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame()) != null) {
			((DiagramView) MainFrame.getInstance().getDesktop()
					.getSelectedFrame()).transformToUserSpace(position);

			((DiagramView) MainFrame.getInstance().getDesktop()
					.getSelectedFrame()).setMouseCursor(position);
		}
	}

	public void mouseDragged(MouseEvent e) {

		if (mouseButton == MouseEvent.BUTTON1) {
			Point position = e.getPoint();
			((DiagramView) MainFrame.getInstance().getDesktop()
					.getSelectedFrame()).transformToUserSpace(position);

			handleInMotion = ((DiagramView) MainFrame.getInstance()
					.getDesktop().getSelectedFrame())
					.getDeviceAndHandleForPoint(position);
			if (handleInMotion != null) {
				// med.startResizeState();
			} else {
				// nije selektovan handle, da li je selektovan element
				elementInMotion = med.getModel().getDeviceAtPosition(position);
				if (elementInMotion != -1) {
					// selektovan je element ili grupa elemenata
					// preci u MoveState
					med.startMoveState();
					// System.out.println("Move");
					return;
				} else
					// nije pogodjen element, prelazimo u Laso stanje
					med.startLassoState();
			}
		}
	}

	@Override
	public String toString() {
		return "SelectState";
	}

}
