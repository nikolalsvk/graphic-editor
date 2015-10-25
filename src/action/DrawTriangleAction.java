package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class DrawTriangleAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8683031052770154881L;
	
	public DrawTriangleAction() {
		putValue(NAME, "Triangle");
		putValue(SHORT_DESCRIPTION, "Draw a triangle");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/triangle.png")));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).getDiagram().startTriangleState();
	}

}
