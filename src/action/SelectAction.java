package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class SelectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7896588298282350611L;
	
	public SelectAction() {
		putValue(NAME, "Select Elements");
		putValue(SHORT_DESCRIPTION, "Select");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/Cursor-Select-icon.png")));
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).getDiagram().startSelectState();
	}
	
	
}
