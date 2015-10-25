package commands;

import java.awt.Point;

import model.DiagramModel;
import model.DiagramSelectionModel;
import model.elements.CircleElement;
import model.elements.DiagramDevice;
import model.elements.HexagonElement;
import model.elements.RectangleElement;
import model.elements.TriangleElement;

public class AddElementCommand extends AbstractCommand {

	DiagramModel model;
	Point lastPosition;
	DiagramDevice device = null;
	DiagramSelectionModel selectionModel;
	String deviceType;

	public AddElementCommand(DiagramModel model, DiagramSelectionModel selectionModel,
			Point lastPosition, String deviceType) {
		this.model = model;
		this.lastPosition = lastPosition;
		this.selectionModel = selectionModel;
		this.deviceType = deviceType;
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		if (device == null)
			if (deviceType == "CircleState") {
				device = CircleElement.createDefault(lastPosition,
						model.getElementCount());
			} else if (deviceType == "RectangleState") {
				device = RectangleElement.createDefault(lastPosition,
						model.getElementCount());
			} else if (deviceType == "TriangleState") {
				device = TriangleElement.createDefault(lastPosition,
						model.getElementCount());
			} else if (deviceType == "HexagonState") {
				device = HexagonElement.createDefault(lastPosition,
						model.getElementCount());
			}
		
		selectionModel.removeAllFromSelectionList();
		model.addDiagramElements(device);
		selectionModel.addToSelectionList(device);
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		selectionModel.removeAllFromSelectionList();
		model.removeElement(device);
	}

}
