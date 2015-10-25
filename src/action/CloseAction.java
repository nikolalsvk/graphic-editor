package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import model.tree.Diagram;
import model.tree.Project;
import model.tree.Workspace;


import app.MainFrame;
import app.tools.ResizingImage;

public class CloseAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3362212760926153279L;

	public CloseAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(NAME, "Close");
		putValue(SHORT_DESCRIPTION, "Close");
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/closeone.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Workspace)
			return;

		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Project) {

			Project p = (Project) MainFrame.getInstance().getWorkspaceTree()
					.getLastSelectedPathComponent();
			int pIndex = ((Workspace) MainFrame.getInstance()
					.getWorkspaceModel().getRoot()).getProjectIndex(p);
			
			while(!p.getDiagramList().isEmpty()) {
				for (int j = 0; j < MainFrame.getInstance().getDesktop()
						.getAllFrames().length; j++) {
					if(p.getLast() == null)
						continue;
					if ((p.getDiagram(0).getName()).equals(MainFrame.getInstance()
							.getDesktop().getAllFrames()[j].getName())) {
						MainFrame
								.getInstance()
								.getWorkspaceTree()
								.removeDiagramViewsFromList(
										MainFrame
												.getInstance()
												.getWorkspaceTree()
												.getDiagramIndex(
														(DiagramView) MainFrame
																.getInstance()
																.getDesktop()
																.getAllFrames()[j]));
						 
						MainFrame.getInstance().getDesktop().getAllFrames()[j]
								.dispose();

						((Workspace) MainFrame.getInstance()
								.getWorkspaceModel().getRoot())
								.getProjectList().get(pIndex).getDiagramList()
								.remove(p.getDiagram(0));
					}
				}
			}
			MainFrame.getInstance().getWorkspaceTree().setSelectionPath(null);
			
			((Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot())
					.getProjectList().remove(p);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		}

		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Diagram) {
			Diagram d = (Diagram) MainFrame.getInstance().getWorkspaceTree()
					.getLastSelectedPathComponent();
			
			Project projectPar = (Project) d.getParent();
			int projectParIndex = ((Workspace) MainFrame.getInstance()
					.getWorkspaceModel().getRoot()).getProjectList().indexOf(
					projectPar);

			for (int i = 0; i < MainFrame.getInstance().getDesktop()
					.getAllFrames().length;) {
				if (d.getName().equals(
						MainFrame.getInstance().getDesktop().getAllFrames()[i]
								.getName())) {
					MainFrame
							.getInstance()
							.getWorkspaceTree()
							.removeDiagramViewsFromList(
									MainFrame
											.getInstance()
											.getWorkspaceTree()
											.getDiagramIndex(
													(DiagramView) MainFrame
															.getInstance()
															.getDesktop()
															.getAllFrames()[i]));

					MainFrame.getInstance().getDesktop().getAllFrames()[i]
							.dispose();
					
					((Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot())
					.getProjectList()
					.get(projectParIndex)
					.getDiagramList()
					.remove(d);
					
					
				}
				break;
			}
			
			MainFrame.getInstance().getWorkspaceTree().setSelectionPath(null);

			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		}
	}
}
