package action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.MainFrame;
import app.tools.ResizingImage;

public class TileWindowsVerticallyAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7996932103956617203L;

	public TileWindowsVerticallyAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.ALT_MASK));
		putValue(NAME, "Tile Windows Vertically");
		putValue(SMALL_ICON, ResizingImage.resizeImg(new ImageIcon(
				"img/tile-vertically.png")));
		putValue(SHORT_DESCRIPTION, "Tiles windows vertically");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (MainFrame.getInstance().getDesktop().getAllFrames().length == 0)
			return;

		int rows = (int) Math.ceil(MainFrame.getInstance().getDesktop()
				.getAllFrames().length
				/ Math.floor(Math.sqrt(MainFrame.getInstance().getDesktop()
						.getAllFrames().length)));
		int columns = (int) Math.sqrt(MainFrame.getInstance().getDesktop()
				.getAllFrames().length);

		int x = (int) MainFrame.getInstance().getDesktop().getSize().getWidth()
				/ rows;
		int y = (int) MainFrame.getInstance().getDesktop().getSize().getHeight()
				/ columns;

		int k = 0;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				if (k < MainFrame.getInstance().getDesktop().getAllFrames().length) {
					MainFrame.getInstance().getDesktop().getAllFrames()[k]
							.setLocation(x * i, y * j);
					MainFrame.getInstance().getDesktop().getAllFrames()[k]
							.setSize(x, y);
					k++;
				}
			}

	}

}
