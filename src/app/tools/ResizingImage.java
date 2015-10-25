package app.tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizingImage {

	public static ImageIcon resizeImg(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(22, 22,
				java.awt.Image.SCALE_SMOOTH);
		return icon = new ImageIcon(newimg);
	}

	public static ImageIcon resizeImg(ImageIcon icon, int WIDTH, int LENGTH) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(WIDTH, LENGTH,
				java.awt.Image.SCALE_SMOOTH);
		return icon = new ImageIcon(newimg);
	}
}
