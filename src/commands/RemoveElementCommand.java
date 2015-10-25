package commands;

import java.util.ArrayList;

import model.DiagramModel;
import model.DiagramSelectionModel;
import model.elements.DiagramDevice;
import model.elements.DiagramElement;

public class RemoveElementCommand extends AbstractCommand {
	
	DiagramModel model;
	DiagramSelectionModel selectionModel;
	ArrayList<DiagramElement> selectedElements;
	
	@SuppressWarnings("unchecked")
	public RemoveElementCommand(DiagramModel model, DiagramSelectionModel selectionModel) {
		this.model = model;
		this.selectionModel = selectionModel;
		this.selectedElements = (ArrayList<DiagramElement>) selectionModel.getSelectionList().clone();
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		selectionModel.removeAllFromSelectionList();
		for (DiagramElement selected : selectedElements) {
			model.removeElement(selected);
		}
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		selectionModel.removeAllFromSelectionList();
		for (DiagramElement selected : selectedElements) {
			model.addDiagramElements((DiagramDevice) selected);
			selectionModel.addToSelectionList(selected);
		}
	}

}
