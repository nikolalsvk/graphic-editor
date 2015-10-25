package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

public class ZoomOutAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3668588258680925537L;
	
	public ZoomOutAction() {
		//putValue(SMALL_ICON, loadIcon("../images/about.png"));
		putValue(NAME, "Zoom out");
		putValue(SHORT_DESCRIPTION, "Zoom out");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/zoom_out.png")));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame()).zoomOut();
	}
	
}
