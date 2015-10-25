package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import model.tree.Workspace;



import app.MainFrame;
import app.tools.ResizingImage;

public class CloseAllAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796908381607169744L;

	public CloseAllAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(NAME, "Close all");
		putValue(SHORT_DESCRIPTION, "Close all diagrams and projects");
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/closeall.png")));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		while(!((Workspace) MainFrame.getInstance()
				.getWorkspaceModel().getRoot()).getProjectList().isEmpty()) {
			while (!((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot()).getProjectList().get(0).getDiagramList()
					.isEmpty()) {
				
				((Workspace) MainFrame.getInstance().getWorkspaceModel()
						.getRoot()).getProjectList().get(0).getDiagramList()
						.remove(((Workspace) MainFrame.getInstance().getWorkspaceModel()
								.getRoot()).getProjectList().get(0).getDiagramList().get(0));
			}
			((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot()).getProjectList().remove(((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot()).getProjectList().get(0));
		}
		
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		
		while(MainFrame.getInstance().getDesktop().getAllFrames().length != 0) {
			for(int i = 0; i < MainFrame.getInstance().getDesktop().getAllFrames().length; i++)	
				MainFrame.getInstance().getDesktop().getAllFrames()[i].dispose();
		}
	}
	
	

}
