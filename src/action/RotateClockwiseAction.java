package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

import commands.RotateElementCommand;

public class RotateClockwiseAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7069823760918794758L;

	public RotateClockwiseAction() {
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/rotateright.png")));
		putValue(NAME, "Rotate Clockwise");
		putValue(SHORT_DESCRIPTION, "Rotate right");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		DiagramView view = (DiagramView) (MainFrame.getInstance().getDesktop()
				.getSelectedFrame());
		if (view != null) {
			view.getCommandManager().addCommand(
					new RotateElementCommand(view, view.getDiagram()
							.getSelectionModel(), "CC"));
		}

		view.updatePerformed(null);
	}

}
