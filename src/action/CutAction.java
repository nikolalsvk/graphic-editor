package action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.tools.ResizingImage;

public class CutAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 333301074481182328L;

	public CutAction() {
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cuts");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/cut.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
