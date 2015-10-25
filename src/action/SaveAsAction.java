package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.tools.ResizingImage;

public class SaveAsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2989233383704127875L;

	public SaveAsAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_S & KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		putValue(NAME, "Save as");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/save-icon.png")));
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
