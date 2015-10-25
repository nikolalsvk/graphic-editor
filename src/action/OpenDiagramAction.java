package action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.tools.ResizingImage;

public class OpenDiagramAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 335293254299753115L;

	public OpenDiagramAction() {
		putValue(NAME, "Open diagram");		
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/linedpaper32.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
