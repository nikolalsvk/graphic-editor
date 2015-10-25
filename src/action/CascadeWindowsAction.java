package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class CascadeWindowsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6375351742558185284L;
	
	public CascadeWindowsAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(NAME, "Cascade Windows");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/cascade-512.png")));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (MainFrame.getInstance().getDesktop().getAllFrames().length == 0)
			return;
		
		int i;
		JInternalFrame[] pom = new JInternalFrame[MainFrame.getInstance().getDesktop().getAllFrames().length];
		
		for (i = 0; i < pom.length; i++) {
			pom[i] = MainFrame.getInstance().getDesktop().getAllFrames()[i];
		}
		
		for (i = pom.length - 1; i >= 0; i--) {
			pom[i].setLocation((pom.length - i) * 30, (pom.length - i) * 30);
			pom[i].setSize(300, 400);

			try {
				pom[i].setSelected(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
