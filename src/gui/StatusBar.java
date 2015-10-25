package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1696597433259221588L;

	private StatusPane status = new StatusPane("State");
	private StatusPane elementType = new StatusPane("Element type");
	private StatusPane elementName = new StatusPane("Element name");
	private StatusPane position = new StatusPane("Position");
	private StatusPane dimension = new StatusPane("Dimension");

	public StatusBar() {
		super();

		setLayout(new GridLayout(1, 5));
		add(status);
		add(elementType);
		add(elementName);
		add(position);
		add(dimension);

		/*
		 * setPreferredSize(new Dimension(200, 25)); this.setLayout(new
		 * GridLayout(1, 5));
		 * 
		 * JTextField position = new JTextField("Position:");
		 * position.setAlignmentX(CENTER_ALIGNMENT);
		 * position.setHorizontalAlignment(JTextField.CENTER);
		 * position.setBackground(getBackground()); position.setBorder(new
		 * EtchedBorder(Color.RED, Color.red)); position.setEditable(false);
		 * 
		 * JTextField graphicEd1 = new JTextField("GraphicED alfa");
		 * graphicEd1.setAlignmentX(CENTER_ALIGNMENT);
		 * graphicEd1.setHorizontalAlignment(JTextField.CENTER);
		 * graphicEd1.setBackground(getBackground()); graphicEd1.setBorder(new
		 * EtchedBorder()); graphicEd1.setEditable(false);
		 * 
		 * JTextField graphicEd2 = new JTextField("GraphicED alfa");
		 * graphicEd2.setAlignmentX(CENTER_ALIGNMENT);
		 * graphicEd2.setHorizontalAlignment(JTextField.CENTER);
		 * graphicEd2.setBackground(getBackground()); graphicEd2.setBorder(new
		 * EtchedBorder()); graphicEd2.setEditable(false);
		 * 
		 * JTextField graphicEd3 = new JTextField("GraphicED alfa");
		 * graphicEd3.setAlignmentX(CENTER_ALIGNMENT);
		 * graphicEd3.setHorizontalAlignment(JTextField.CENTER);
		 * graphicEd3.setBackground(getBackground()); graphicEd3.setBorder(new
		 * EtchedBorder()); graphicEd3.setEditable(false);
		 * 
		 * JTextField graphicEd4 = new JTextField("GraphicED alfa");
		 * graphicEd4.setAlignmentX(CENTER_ALIGNMENT);
		 * graphicEd4.setHorizontalAlignment(JTextField.CENTER);
		 * graphicEd4.setBackground(getBackground()); graphicEd4.setBorder(new
		 * EtchedBorder()); graphicEd4.setEditable(false);
		 * 
		 * add(position); add(graphicEd1); add(graphicEd2); add(graphicEd3);
		 * add(graphicEd4);
		 */
	}

	private class StatusPane extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3535738517708674670L;

		public StatusPane(String text) {
			setBorder(BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			setBackground(Color.GRAY);
			setPreferredSize(new Dimension(180, 20));
			setHorizontalAlignment(CENTER);
			setText(text);
		}
	}

	public void setStatus(String status) {
		this.status.setText(status);
	}

	public void setElementType(String elementType) {
		this.elementType.setText(elementType);
	}

	public void setElementName(String elementName) {
		this.elementName.setText(elementName);
	}

	public void setPosition(String position) {
		this.position.setText(position);
	}

	public void setDimension(String dimension) {
		this.dimension.setText(dimension);
	}
	
	public void resetStatusBar() {
		setElementType("Element type");
		setElementName("Element name");
		setDimension("Dimension");
	}
}
