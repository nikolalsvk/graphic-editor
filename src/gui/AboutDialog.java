package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import app.tools.ResizingImage;

public class AboutDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4147155878851661385L;

	public AboutDialog(Frame owner) {
		super(owner, "About", true);
		
		// loading img
/*		ImageIcon img = new ImageIcon("img/djuza.jpg");
		img = ResizingImage.resizeImg(img, 150, 150);
		JLabel imgLbl = new JLabel();
		imgLbl.setIcon(img);
		imgLbl.setHorizontalAlignment(JLabel.CENTER);
		ImagePanel imgPan = new ImagePanel("img/djuza.jpg");
		paintComponents(imgPan);*/
		
		JLabel imgLab = new JLabel(ResizingImage.resizeImg(new ImageIcon("img/djuza.jpg"), 250, 200));
		imgLab.setAlignmentX(CENTER_ALIGNMENT);
		imgLab.setBorder(new EtchedBorder());
		
		// button part
		JButton okBtn = new JButton(" OK ");
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getMe().dispose();
			}
		});
		
		JPanel panY = new JPanel();
		panY.setLayout(new BoxLayout(panY, BoxLayout.Y_AXIS));
		panY.add(imgLab);
		///getContentPane().add(panY, BorderLayout.NORTH);
		
		JPanel panDown = new JPanel();
		panDown.add(okBtn);
				
		JTextArea txtMe = new JTextArea("\tNikola Djuza \n\n\tRA6-2012 \n\tnikolaseap@gmail.com");
		txtMe.setEditable(false);
		txtMe.setSize(new Dimension(150, 150));
		txtMe.setBackground(getBackground());
		txtMe.setBorder(new EtchedBorder());
		txtMe.setAlignmentX(CENTER_ALIGNMENT);
		txtMe.setFont(new Font("Tahoma", Font.BOLD, 15));
		panY.add(txtMe);
		
		// window settings
		add(panY, BorderLayout.CENTER);
		add(panDown, BorderLayout.SOUTH);
		setSize(350, 350);
		setResizable(false);
		setLocationRelativeTo(owner);
	}
	
	public AboutDialog getMe() {
		return this;
	}
}
