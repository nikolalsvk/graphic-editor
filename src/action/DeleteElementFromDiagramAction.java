package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import model.elements.DiagramElement;
import model.tree.Diagram;
import app.MainFrame;
import app.tools.ResizingImage;

import commands.RemoveElementCommand;

public class DeleteElementFromDiagramAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1408365403516355918L;

	public DeleteElementFromDiagramAction() {
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Deletes element");
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/trash.png")));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Diagram) {
			Diagram d = (Diagram) MainFrame.getInstance().getWorkspaceTree()
					.getLastSelectedPathComponent();
			DiagramView view = (DiagramView) MainFrame.getInstance()
					.getDesktop().getSelectedFrame();
			d.startSelectState();

			if (!d.getSelectionModel().getSelectionList().isEmpty()) {
				view.getCommandManager().addCommand(
						new RemoveElementCommand(view.getDiagram().getModel(),
								view.getDiagram().getSelectionModel()));

				Iterator<DiagramElement> iter = d.getSelectionModel()
						.getSelectionListIterator();
				while (iter.hasNext()) {
					DiagramElement element = iter.next();
					d.getModel().removeElement(element);
				}
				d.getSelectionModel().removeAllFromSelectionList();
			}

		}
	}

}
