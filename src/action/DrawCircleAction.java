package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class DrawCircleAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566028749850063446L;
	
	public DrawCircleAction() {
		putValue(NAME, "Draw Circle");
		putValue(SHORT_DESCRIPTION, "Draws a circle");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/circle-512.png")));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).getDiagram().startCircleState();
	}

}
