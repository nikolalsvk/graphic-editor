package gui;

import java.awt.Dimension;

import javax.swing.JToolBar;

import action.CascadeWindowsAction;
import action.CloseAction;
import action.CloseAllAction;
import action.CopyAction;
import action.CutAction;
import action.NewDiagramAction;
import action.NewProjectAction;
import action.NextWindowAction;
import action.OpenDiagramAction;
import action.OpenProjectAction;
import action.PreviousWindowAction;
import action.RotateClockwiseAction;
import action.RotateCounterClockwiseAction;
import action.SaveAction;
import action.SaveAsAction;
import action.TileWindowsHorizontallyAction;
import action.TileWindowsVerticallyAction;
import action.ZoomInAction;
import action.ZoomOutAction;
import action.command.RedoAction;
import action.command.UndoAction;

public class Toolbar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5707394191276063225L;

	public Toolbar() {
		setPreferredSize(new Dimension(150, 33));
		add(new NewProjectAction());
		add(new NewDiagramAction());
		addSeparator();
		add(new OpenProjectAction());
		add(new OpenDiagramAction());
		addSeparator();
		add(new SaveAction());
		add(new SaveAsAction());
		addSeparator();
		add(new CopyAction());
		add(new CutAction());
		addSeparator();
		add(new CloseAction());
		add(new CloseAllAction());
		addSeparator();
		add(new CascadeWindowsAction());
		add(new TileWindowsVerticallyAction());
		add(new TileWindowsHorizontallyAction());
		add(new PreviousWindowAction());
		add(new NextWindowAction());
		addSeparator();
		add(new RotateClockwiseAction());
		add(new RotateCounterClockwiseAction());
		addSeparator();
		add(new ZoomInAction());
		add(new ZoomOutAction());
		addSeparator();
		add(new UndoAction());
		add(new RedoAction());
		
		setFloatable(false);
	}
}
