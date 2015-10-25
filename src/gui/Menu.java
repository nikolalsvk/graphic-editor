package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import action.CallAboutDialog;
import action.CascadeWindowsAction;
import action.CloseAction;
import action.CloseAllAction;
import action.CopyAction;
import action.CutAction;
import action.ExitAppAction;
import action.ExportProjectAction;
import action.ImportProjectAction;
import action.NewDiagramAction;
import action.NewProjectAction;
import action.NextWindowAction;
import action.OpenDiagramAction;
import action.OpenProjectAction;
import action.PreviousWindowAction;
import action.PrintDocumentAction;
import action.SaveAction;
import action.SaveAsAction;
import action.TileWindowsHorizontallyAction;
import action.TileWindowsVerticallyAction;
import action.ZoomInAction;
import action.ZoomOutAction;
import action.command.RedoAction;
import action.command.UndoAction;

public class Menu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5617155576631422259L;

	public Menu() {
		// file menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenu miNew = new JMenu("New");
		miNew.setMnemonic('N');
		fileMenu.add(miNew);
		miNew.add(new NewProjectAction());
		miNew.add(new NewDiagramAction());

		JMenu miOpen = new JMenu("Open");
		miOpen.setMnemonic('O');
		fileMenu.add(miOpen);
		miOpen.add(new OpenProjectAction());
		miOpen.add(new OpenDiagramAction());

		JMenu miSave = new JMenu("Save");
		miSave.setMnemonic('S');
		fileMenu.add(miSave);
		miSave.add(new SaveAction());
		miSave.add(new SaveAsAction());

		JMenu miClose = new JMenu("Close");
		miClose.setMnemonic('C');
		miClose.add(new CloseAction());
		miClose.add(new CloseAllAction());
		fileMenu.add(miClose);
		fileMenu.addSeparator();
		fileMenu.add(new PrintDocumentAction());
		fileMenu.addSeparator();
		fileMenu.add(new ImportProjectAction());
		fileMenu.add(new ExportProjectAction());
		fileMenu.add(new ExitAppAction());

		// edit menu
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		editMenu.add(new CopyAction());
		editMenu.add(new CutAction());

		// view menu
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic('V');
		viewMenu.add(new ZoomInAction());
		viewMenu.add(new ZoomOutAction());

		// tools menu
		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic('T');
		toolsMenu.add(new UndoAction());
		toolsMenu.add(new RedoAction());

		// window menu
		JMenu windowMenu = new JMenu("Window");
		windowMenu.setMnemonic('W');
		windowMenu.add(new CascadeWindowsAction());
		windowMenu.add(new TileWindowsVerticallyAction());
		windowMenu.add(new TileWindowsHorizontallyAction());
		windowMenu.addSeparator();
		windowMenu.add(new PreviousWindowAction());
		windowMenu.add(new NextWindowAction());

		// help menu
		JMenu helpMenu = new JMenu("About");
		helpMenu.setMnemonic('A');
		helpMenu.add(new CallAboutDialog());

		// adding components to JMenu
		add(fileMenu);
		add(editMenu);
		add(viewMenu);
		add(toolsMenu);
		add(windowMenu);
		add(helpMenu);
	}
}
