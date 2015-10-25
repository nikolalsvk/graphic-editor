package model.tree;

import gui.tree.DiagramView;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import model.DiagramModel;
import model.DiagramSelectionModel;
import state.StateManager;
import app.MainFrame;

public class Diagram implements TreeNode, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3176343259428433653L;

	private String name = null;
	private Project parent;

	private DiagramModel model = new DiagramModel();

	private StateManager stateManager = new StateManager(this);

	private DiagramSelectionModel selectionModel;

	// state manager

	public void startSelectState() {
		stateManager.setSelectState();
	}

	public void startRectangleState() {
		selectionModel.removeAllFromSelectionList();
		stateManager.setRectangleState();
	}
	
	public void startHexagonState() {
		selectionModel.removeAllFromSelectionList();
		stateManager.setHexagonState();
	}

	public void startCircleState() {
		selectionModel.removeAllFromSelectionList();
		stateManager.setCircleState();
	}

	public void startTriangleState() {
		selectionModel.removeAllFromSelectionList();
		stateManager.setTriangleState();
	}

	public void startLassoState() {
		stateManager.setLassoState();
	}
	
	public void startMoveState() {
		stateManager.setMoveState();
	}
	
	public void startResizeState() {
		stateManager.setResizeState();
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	// selection

	public DiagramSelectionModel getSelectionModel() {
		if (selectionModel == null)
			selectionModel = new DiagramSelectionModel();
		return selectionModel;
	}

	// konstruktor

	public Diagram() {
		startSelectState();
	}

	public DiagramView getDiagram() {
		DiagramView[] views = (DiagramView[]) MainFrame.getInstance().getDesktop()
				.getAllFrames();
		
		if(MainFrame.getInstance().getDesktop().getSelectedFrame().getName().equals(this.name)) {
			return (DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame();
		}
		
		for(int i = 0; i < views.length; i++) {
			if(views[i].getName().equals(this.name)) {
				return views[i];
			}
		}
		
		return null;
	}

	public void setDiagram(DiagramView diagram) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// diagram.setNameEdited(name);
		this.name = name;
	}

	public DiagramModel getModel() {
		return model;
	}

	public void setModel(DiagramModel model) {
		this.model = model;
	}

	public String toString() {
		return name;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
}
