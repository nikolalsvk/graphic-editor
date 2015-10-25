package action;

import gui.tree.DiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import model.tree.Project;
import app.MainFrame;
import app.tools.DiagramFileFilter;
import app.tools.ResizingImage;

public class OpenProjectAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3332493270403250271L;

	public OpenProjectAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON,
				ResizingImage.resizeImg(new ImageIcon("img/folder32.png")));
		putValue(NAME, "Open project");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFileFilter());
		
		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			// kreiramo ObjectInputStream na izabrani fajl sa .gpf ekstenzijom
			ObjectInputStream os = null;
			try {
				os = new ObjectInputStream(new FileInputStream(
						jfc.getSelectedFile()));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			// izvlacenje projekta
			Project p = null;
			try {
				// poziv metode readObject() koja vrsi dubinsku deserijalizaciju
				p = (Project) os.readObject();
			} catch (ClassNotFoundException ec) {
				ec.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			MainFrame.getInstance().getWorkspaceTree().addProject(p);
			
			for (int i = 0; i < p.getDiagramCount(); i++) {
				DiagramView view = new DiagramView();
				p.getDiagram(i).getModel().addUpdateListener(p);
				view.setDiagram(p.getDiagram(i));
				MainFrame.getInstance().getDesktop().add(view);
				MainFrame.getInstance().getWorkspaceTree().addDiagramViewsToList(view);
			}
		}
	}
}
