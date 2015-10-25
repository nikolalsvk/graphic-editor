package action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.tools.ResizingImage;

public class DrawLineAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5410130316316726653L;
	
	public DrawLineAction() {
		putValue(NAME, "Draw a line");
		putValue(SHORT_DESCRIPTION, "Draws a line");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/LineIcon.png")));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
