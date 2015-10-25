package action;

import gui.AboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class CallAboutDialog extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2937518885323962692L;

	public CallAboutDialog() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_A, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/lightbulb32.png")));
		putValue(NAME, "About");
		putValue(SHORT_DESCRIPTION, "About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AboutDialog about = new AboutDialog(MainFrame.getInstance());
		about.setVisible(true);
	}

}
