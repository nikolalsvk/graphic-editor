package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class DrawRectangleAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3752999903226039426L;

	public DrawRectangleAction() {
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/rectangle.png")));
		putValue(NAME, "Rectangle");
		putValue(SHORT_DESCRIPTION, "Draws a rectangle");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.getDiagram().startRectangleState();
	}

}
