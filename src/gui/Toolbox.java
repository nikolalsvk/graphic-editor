package gui;

import java.awt.Dimension;

import javax.swing.JToolBar;

import action.DeleteElementFromDiagramAction;
import action.DrawCircleAction;
import action.DrawHexagonAction;
import action.DrawLineAction;
import action.DrawRectangleAction;
import action.DrawTriangleAction;
import action.SelectAction;

public class Toolbox extends JToolBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 479085124491459408L;

	public Toolbox() {
		super(VERTICAL);
		setPreferredSize(new Dimension(33, 150));
		add(new SelectAction());
		add(new DrawLineAction());
		add(new DrawCircleAction());
		add(new DrawRectangleAction());
		add(new DrawTriangleAction());
		add(new DrawHexagonAction());
		addSeparator(new Dimension(0, 15));
		add(new DeleteElementFromDiagramAction());
	}
}
