package action.command;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class UndoAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5990026660071655595L;
	
	public UndoAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undoes current action you did");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon("img/undo.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame()).getCommandManager().undoCommand();
	}

}
