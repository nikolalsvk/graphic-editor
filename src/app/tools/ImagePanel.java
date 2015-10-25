package app.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImagePanel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7250647043727754409L;

	private BufferedImage image;

	public ImagePanel(String imageURL) {
		try {
			image = ImageIO.read(new File("image name and path"));
		} catch (IOException ex) {
			// handle exception...
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); 
	}
}
