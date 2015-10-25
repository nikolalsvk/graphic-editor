package action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.tools.ResizingImage;

public class CopyAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2366672681563144494L;
	
	public CopyAction() {
		putValue(NAME, "Copy");
		putValue(SHORT_DESCRIPTION, "Copy");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/copy.png")));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
