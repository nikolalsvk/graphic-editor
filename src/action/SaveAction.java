package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import model.tree.Project;
import app.MainFrame;
import app.tools.DiagramFileFilter;
import app.tools.ResizingImage;

public class SaveAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5530938978243564359L;

	public SaveAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Saves");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon(
				"img/programming-save-icon.png")));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Project) {

			Project p = (Project) MainFrame.getInstance().getWorkspaceTree()
					.getLastSelectedPathComponent();
			File projectFile = p.getProjectFile();

			JFileChooser jfc = new JFileChooser();
			jfc.setFileFilter(new DiagramFileFilter());

			if (!p.isChanged()) {
				return;
			}

			if (p.getProjectFile() == null) {
				if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
					projectFile = jfc.getSelectedFile();
				} else {
					return;
				}
			}

			ObjectOutputStream os;
			try {
				os = new ObjectOutputStream(new FileOutputStream(projectFile));
				os.writeObject(p);
				p.setProjectFile(projectFile);
				p.setChanged(false);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			
		}
	}
}
