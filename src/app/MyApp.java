package app;

import javax.swing.ImageIcon;


public class MyApp {

	public static void main(String[] args) {

		MainFrame frame = MainFrame.getInstance();
		
		ImageIcon img = new ImageIcon("img/djuza.jpg");
		frame.setIconImage(img.getImage());
		frame.setVisible(true);
	}
}
