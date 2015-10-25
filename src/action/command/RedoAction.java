package action.command;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class RedoAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6723386171894988056L;
	
	public static boolean redoClicked = false;
	
	public RedoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		putValue(NAME, "Redo");
		putValue(SHORT_DESCRIPTION, "Redoes current action you took");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/redo.png")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		RedoAction.redoClicked = true;
		
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame()).getCommandManager().doCommand();
	}

	public static boolean isRedoClicked() {
		return redoClicked;
	}

	public static void setRedoClicked(boolean redoClicked) {
		RedoAction.redoClicked = redoClicked;
	}

}
