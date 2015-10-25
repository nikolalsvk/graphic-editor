package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import app.MainFrame;
import app.tools.ResizingImage;

import commands.RotateElementCommand;

public class RotateCounterClockwiseAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5442008917923328992L;

	public RotateCounterClockwiseAction() {
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/rotateleft.png")));
		putValue(NAME, "Rotate CounterClockwise");
		putValue(SHORT_DESCRIPTION, "Rotate left");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		Iterator<DiagramElement> it = ((DiagramView) (MainFrame.getInstance()
				.getDesktop().getSelectedFrame())).getDiagram()
				.getSelectionModel().getSelectionListIterator();

		while (it.hasNext()) {
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() - Math.PI / 2);
				
				 // device.setSize(new Dimension( (int)
				 // device.getInitialSize().getHeight(), (int) device
				 // .getInitialSize().getWidth()));
				 
			}
		}
		 */
		DiagramView view = (DiagramView) (MainFrame.getInstance().getDesktop()
				.getSelectedFrame());
		if (view != null) {
			view.getCommandManager().addCommand(
					new RotateElementCommand(view, view.getDiagram()
							.getSelectionModel(), "CW"));
		}

		view.updatePerformed(null);
		
		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.updatePerformed(null);
	}

}
