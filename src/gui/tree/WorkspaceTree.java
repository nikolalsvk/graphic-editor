package gui.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import model.tree.Diagram;
import model.tree.Project;
import model.tree.WorkspaceModel;

import app.MainFrame;

public class WorkspaceTree extends JTree implements TreeSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4103080408620022917L;
	public ArrayList<DiagramView> diagramViews = new ArrayList<DiagramView>();

	public WorkspaceTree() {
		addTreeSelectionListener(this);
		setCellRenderer(new WorkspaceTreeCellRenderer());
		setCellEditor(new WorkspaceTreeEditor(this,
				new DefaultTreeCellRenderer()));
		setEditable(true);

		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					TreePath path = MainFrame.getInstance().getWorkspaceTree()
							.getSelectionPath();
					for (int i = 0; i < path.getPathCount(); i++) {
						if (path.getPathComponent(i) instanceof Diagram) {
							Diagram d = (Diagram) path.getPathComponent(i);

							for (int j = 0; j < MainFrame.getInstance()
									.getDesktop().getAllFrames().length; j++) {
								if (d.getName().equals(
										MainFrame.getInstance().getDesktop()
												.getAllFrames()[j].getName())) {
									if (!MainFrame.getInstance().getDesktop()
											.getAllFrames()[j].isVisible()) {
										MainFrame.getInstance().getDesktop()
												.getAllFrames()[j]
												.setVisible(true);
										MainFrame
												.getInstance()
												.getWorkspaceTree()
												.addDiagramViewsToList(
														(DiagramView) MainFrame.getInstance()
																.getDesktop()
																.getAllFrames()[j]);
									}
								}
							}
						}
					}
				}
			}
		};
		addMouseListener(ml);
	}

	public void addProject(Project project) {
		((WorkspaceModel) getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

		TreePath path = e.getPath();
		for (int i = 0; i < path.getPathCount(); i++) {
			if (path.getPathComponent(i) instanceof Diagram) {
				Diagram d = (Diagram) path.getPathComponent(i);
				JInternalFrame[] frame = MainFrame.getInstance().getDesktop()
						.getAllFrames();

				for (int j = 0; j < MainFrame.getInstance().getDesktop()
						.getAllFrames().length; j++) {
					if (d.getName()
							.equals(MainFrame.getInstance().getDesktop()
									.getAllFrames()[j].getName())) {
						if (frame[j].isSelected())
							break;
						try {
							MainFrame.getInstance().getDesktop().getAllFrames()[j]
									.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
				break;
			}
		}

	}

	public ArrayList<DiagramView> getDiagramViews() {
		return diagramViews;
	}

	public void setDiagramViews(ArrayList<DiagramView> diagramViews) {
		this.diagramViews = diagramViews;
	}

	public void addDiagramViewsToList(DiagramView view) {
		diagramViews.add(view);
	}

	public void removeDiagramViewsFromList(int index) {
		diagramViews.remove(index);
	}

	public int getDiagramIndex(DiagramView view) {
		return diagramViews.indexOf(view);
	}
}
