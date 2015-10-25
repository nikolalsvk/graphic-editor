package gui;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8341137281841177587L;
	
	public DesktopPane() {
		super();
		
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}

	public void remove(JInternalFrame[] allFrames) {
		// TODO Auto-generated method stub
		
	}
}
