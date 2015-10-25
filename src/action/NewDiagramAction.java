package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import model.tree.Diagram;
import model.tree.Project;


import app.MainFrame;
import app.tools.ResizingImage;

public class NewDiagramAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2394591170807781091L;

	public NewDiagramAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(NAME, "New diagram");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/linedpaperplus32.png")));
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object p = MainFrame.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		if(p instanceof Project) {
			Diagram d = new Diagram();
			((Project)p).addDiagram(d);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
			
			DiagramView view = new DiagramView();
			view.setDiagram(d);
			d.setDiagram(view);
			MainFrame.getInstance().getWorkspaceTree().addDiagramViewsToList(view);
			MainFrame.getInstance().getDesktop().add(view);
			((Project) p).setChanged(true);
			
			/*
			Object[] pomAL = new Object[3];
			pomAL[0] = ((Workspace)MainFrame.getInstance().getWorkspaceModel().getRoot());
			pomAL[1] = d.getParent();
			pomAL[2] = d;
			MainFrame.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(pomAL));
			*/
			try {
				view.setSelected(true);
			} catch(PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
}
