package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class DrawHexagonAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8665007904502994669L;

	public DrawHexagonAction() {
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/hexagon.png")));
		putValue(NAME, "Hexagon");
		putValue(SHORT_DESCRIPTION, "Draws a hexagon");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.getDiagram().startHexagonState();
	}

}
