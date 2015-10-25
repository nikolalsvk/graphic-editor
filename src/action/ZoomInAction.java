package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class ZoomInAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110636389850667219L;
	
	public ZoomInAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		putValue(NAME, "Zoom in");
		putValue(SHORT_DESCRIPTION, "Zoom in");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/zoom_in.png")));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame()).zoomIn();
	}

}
