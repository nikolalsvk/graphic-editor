package gui.tree;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.tree.Diagram;
import model.tree.Project;


import app.tools.ResizingImage;

public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7900521082961163412L;

	public Component getTreeCellRendererComponent(
			JTree tree, 
			Object value,
			boolean sel, 
			boolean expanded, 
			boolean leaf, 
			int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		
		if (value instanceof Diagram) {
			ImageIcon icon = ResizingImage.resizeImg(new ImageIcon("img/linedpaper32.png"), 18, 18);
			setIcon(icon);
		} else if (value instanceof Project) {
			ImageIcon icon = ResizingImage.resizeImg(new ImageIcon("img/folder32.png"), 18, 18);
			setIcon(icon);
		}
		
		return this;
	}
}
