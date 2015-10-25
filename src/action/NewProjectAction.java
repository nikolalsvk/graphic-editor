package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import model.tree.Diagram;
import model.tree.Project;
import model.tree.Workspace;


import app.MainFrame;
import app.tools.ResizingImage;

public class NewProjectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewProjectAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(NAME, "New project");
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/folderplus32.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Project p = new Project();
		MainFrame.getInstance().getWorkspaceTree().addProject(p);
		Diagram d = new Diagram();
		p.addDiagram(d);

		DiagramView view = new DiagramView();
		view.setDiagram(d);
		d.setDiagram(view);
		MainFrame.getInstance().getWorkspaceTree().addDiagramViewsToList(view);
		MainFrame.getInstance().getDesktop().add(view);
		p.setChanged(true);
		
		Object[] pomAL = new Object[3];
		pomAL[0] = ((Workspace)MainFrame.getInstance().getWorkspaceModel().getRoot());
		pomAL[1] = p;
		pomAL[2] = d;
		MainFrame.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(pomAL));

		try {
			view.setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
