package action;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;

import app.MainFrame;

public class ExitAppAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1656139053676106490L;

	public ExitAppAction() {
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit");
		// putValue(SMALL_ICON, ResizingImage.resizeImg(new
		// ImageIcon("img/Cursor-Select-icon.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		MainFrame.getInstance().dispatchEvent(
				new WindowEvent(MainFrame.getInstance(),
						WindowEvent.WINDOW_CLOSING));
	}
}
