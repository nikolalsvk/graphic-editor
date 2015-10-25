package app.tools;

import gui.tree.DiagramView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeListener;

import model.elements.DiagramElement;
import model.tree.Diagram;
import app.MainFrame;

public class ElementChangeDialog extends JDialog implements WindowListener,
		ColorSelectionModel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576443505863002099L;

	public DiagramElement elementE;
	public DiagramView viewE;
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	
	public ElementChangeDialog(Diagram med, DiagramElement element,
			DiagramView view) {
		setTitle(element.getName() + " editor");
		setModal(true);
		setSize(600, 400);
		setLocationRelativeTo(MainFrame.getInstance());
		setResizable(false);

		this.elementE = element;
		this.viewE = view;

		setLayout(new BorderLayout());
		JColorChooser colorChooser = new JColorChooser(this);
		colorChooser.setSize(new Dimension(600, 280));
		

		nameLabel = new JLabel("Name");
		nameTextField = new JTextField(element.getName());
		descriptionLabel = new JLabel("Description");
		descriptionTextField = new JTextField(element.getDescription());

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
		leftPanel.add(nameLabel);
		leftPanel.add(nameTextField);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		rightPanel.add(descriptionLabel);
		rightPanel.add(descriptionTextField);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(leftPanel);
		bottomPanel.add(rightPanel);

		nameTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				elementE.setName(e.getActionCommand());
				viewE.updatePerformed(null);
			}
		});
		
		descriptionTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				elementE.setDescription(e.getActionCommand());
				viewE.updatePerformed(null);
			}
		});
		
		JButton okBtn = new JButton("OK");
		JButton cancelBtn = new JButton("Cancel");
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getMe().dispatchEvent(new WindowEvent(getMe(), EXIT_ON_CLOSE));
				getMe().dispose();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getMe().dispose();
			}
		});
		
		JPanel buttonLabel = new JPanel();
		buttonLabel.setLayout(new BoxLayout(buttonLabel, BoxLayout.X_AXIS));
		buttonLabel.add(okBtn);
		buttonLabel.add(cancelBtn);
		buttonLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		add(colorChooser, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
		add(buttonLabel, BorderLayout.SOUTH);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		elementE.setName(getNameTextField());
		elementE.setDescription(getDescriptionTextField());
		viewE.updatePerformed(null);
		getMe().dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getSelectedColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChangeListener(ChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectedColor(Color color) {
		// TODO Auto-generated method stub
		elementE.setPaint(color);
		elementE.setStrokeColor(color);
		viewE.updatePerformed(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public String getNameTextField() {
		return nameTextField.getText();
	}

	public JLabel getDescriptionLabel() {
		return descriptionLabel;
	}

	public String getDescriptionTextField() {
		return descriptionTextField.getText();
	}
	
	public ElementChangeDialog getMe() {
		return this;
	}
}
