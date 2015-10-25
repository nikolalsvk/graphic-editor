package model.tree;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import event.UpdateEvent;
import event.UpdateListener;

public class Project implements TreeNode, Serializable, UpdateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7236286694454888241L;
	
	private ArrayList<Diagram> diagramList = new ArrayList<Diagram>();
	private String name = null;
	private int diagramCounter = 1;
	private transient boolean changed;
	private File projectFile;

	public Project() {
		this.changed = false;
		this.projectFile = null;
	}

	public void addDiagram(Diagram diagram) {
		diagram.setName(this.name + " Diagram "
				+ String.valueOf(diagramCounter));
		diagram.setParent(this);
		diagram.getModel().addUpdateListener(this);
		diagramList.add(diagram);
		diagramCounter++;
	}

	public Diagram getLast() {
		if (getDiagramList().size() == 0)
			return null;
		return getDiagram(getDiagramList().size() - 1);
	}

	public int getDiagramCounter() {
		return diagramCounter;
	}

	public void setDiagramCounter(int diagramCounter) {
		this.diagramCounter = diagramCounter;
	}

	public ArrayList<Diagram> getDiagramList() {
		return diagramList;
	}

	public Diagram getDiagram(int index) {
		return diagramList.get(index);
	}

	public int getDiagramIndex(Diagram diagram) {
		return diagramList.indexOf(diagram);
	}

	public int getDiagramCount() {
		return diagramList.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return ((changed ? "* " : "") + name);
	}

	// changed

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		if (this.changed != changed) {
			this.changed = changed;
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		}
	}

	// file part
	
	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}
	
	//

	public void removeDiagram(int index) {
		diagramList.remove(index);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return (Enumeration) diagramList;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		// TODO Auto-generated method stub
		return getDiagram(arg0);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return getDiagramCount();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		// TODO Auto-generated method stub
		return getDiagramIndex((Diagram) arg0);
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		// TODO Auto-generated method stub
		setChanged(true);
	}
}
