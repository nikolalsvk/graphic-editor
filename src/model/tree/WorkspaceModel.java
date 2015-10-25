package model.tree;

import javax.swing.tree.DefaultTreeModel;



public class WorkspaceModel extends DefaultTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1830043019564308734L;

	public WorkspaceModel() {
		super(new Workspace());
	}

	public void addProject(Project project) {
		// TODO Auto-generated method stub
		((Workspace) getRoot()).addProject(project);
	}

	public Object getChild(Object parent, int index) {
		if (parent instanceof Diagram) {
			return null;
		} else if (parent instanceof Workspace) {
			return ((Workspace) parent).getProject(index);
		} else if (parent instanceof Project) {
			return ((Project) parent).getDiagram(index);
		}
		return getRoot();
	}

	public int getChildCount(Object parent) {
		if (parent instanceof Diagram) {
			return 0;
		} else if (parent instanceof Project) {
			return ((Project) parent).getDiagramCount();
		} else if (parent instanceof Workspace) {
			return ((Workspace) parent).getProjectCount();
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		return (node instanceof Diagram);
	}

	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof Diagram) {
			return -1;
		} else if (parent instanceof Workspace) {
			if (child instanceof Project)
				return ((Workspace) parent).getProjectIndex((Project) child);
		} else if (parent instanceof Project) {
			return ((Project) parent).getDiagramIndex((Diagram) child);
		}
		return -1;
	}
	
}
