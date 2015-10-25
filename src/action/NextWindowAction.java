package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import model.tree.Diagram;


import app.MainFrame;
import app.tools.ResizingImage;

public class NextWindowAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3751171544786780908L;

	public NextWindowAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		putValue(NAME, "Next Window");
		putValue(SHORT_DESCRIPTION, "Selects next window");
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/arrowright32.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		TreePath currentSelectedFromTree = MainFrame.getInstance()
				.getWorkspaceTree().getSelectionPath();
		
		if(MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		for (int i = 0; i < currentSelectedFromTree.getPathCount(); i++) {
			if (currentSelectedFromTree.getPathComponent(i) instanceof Diagram) {
				Diagram d = (Diagram) currentSelectedFromTree
						.getPathComponent(i);
				
				int current = 0;
				for(int j = 0; j < MainFrame.getInstance().getWorkspaceTree().getDiagramViews().size(); j++) {
					if(d.getName().equals(MainFrame.getInstance().getWorkspaceTree().getDiagramViews().get(j).getName())) {
						current = j;
						System.out.println("E" + j);
						break;
					} else {
						current = 0;
					}
				}
				
				int next = current + 1;
				
				if(current == MainFrame.getInstance().getWorkspaceTree().getDiagramViews().size()-1) {
					next = 0;
				} else {
					next = current + 1;
				}
				
				for(int k = 0; k < MainFrame.getInstance().getDesktop().getAllFrames().length; k++) {
					if((MainFrame.getInstance().getWorkspaceTree().getDiagramViews().get(next).getName())
							.equals(MainFrame.getInstance().getDesktop().getAllFrames()[k].getName())) {
						try {
							MainFrame.getInstance().getDesktop().getAllFrames()[k].setSelected(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
				}

			}
		}
		

	}
}
