package model.tree;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;



public class Workspace implements TreeNode {

	private ArrayList<Project> projectList = new ArrayList<Project>();
	private int projectCounter = 1;
	
	public Workspace() {
		super();
	}
	
	public String toString() {
		return "Workspace";
	}
	
	public void addProject(Project project) {
		project.setName("Project " + String.valueOf(projectCounter));
		projectList.add(project);
		projectCounter++;
	}
	
	public int getProjectCounter() {
		return projectCounter;
	}

	public void setProjectCounter(int projectCounter) {
		this.projectCounter = projectCounter;
	}

	public ArrayList<Project> getProjectList() {
		return projectList;
	}

	public Project getProject(int index) {
		return projectList.get(index);
	}
	
	public int getProjectIndex(Project project) {
		return projectList.indexOf(project);
	}
	
	public int getProjectCount() {
		return projectList.size();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Enumeration<Diagram> children() {
		return (Enumeration<Diagram>) projectList;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return projectList.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return projectList.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return projectList.indexOf(node);
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
}
