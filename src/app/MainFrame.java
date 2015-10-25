package app;

import gui.DesktopPane;
import gui.Menu;
import gui.StatusBar;
import gui.Toolbar;
import gui.Toolbox;
import gui.tree.DiagramView;
import gui.tree.WorkspaceTree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;

import action.command.CommandActionManager;

import model.tree.WorkspaceModel;



public class MainFrame extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1729233823098776690L;

	private static MainFrame instance = null;
	
	private WorkspaceModel workspaceModel;
    private WorkspaceTree workspaceTree;
    private DesktopPane desktop;
    private StatusBar statusbar;
    private CommandActionManager commandActionManager = new CommandActionManager();
    
    private ArrayList<DiagramView> diagramViews = new ArrayList<DiagramView>();

	private MainFrame() {
		initialise();
	}

	private void initialise() {
		@SuppressWarnings("unused")
		Toolkit kit = Toolkit.getDefaultToolkit();
/*		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2);*/
		setSize(1000, 700);
		setTitle("GraphicEd");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(600, 600));
		

		Menu menu = new Menu();
		setJMenuBar(menu);

		Toolbar toolbar = new Toolbar();
		Toolbox toolbox = new Toolbox();
		
		statusbar = new StatusBar();
		
		// desktop and tree
		desktop = new DesktopPane();
		workspaceTree = new WorkspaceTree();
		workspaceModel = new WorkspaceModel();
		workspaceTree.setModel(workspaceModel);
		ToolTipManager.sharedInstance().registerComponent(workspaceTree);
		
		JScrollPane scroll = new JScrollPane(workspaceTree);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane desktopScroll = new JScrollPane(desktop);
		desktopScroll.setPreferredSize(new Dimension(600, 600));
		desktopScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		desktopScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktopScroll);
		splitPane.setDividerLocation(240);
		splitPane.setEnabled(false);
		addWindowListener(this);
		
		add(toolbar, BorderLayout.NORTH);
		add(toolbox, BorderLayout.EAST);
		add(splitPane, BorderLayout.CENTER);
		add(statusbar, BorderLayout.SOUTH);
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		
		return instance;
	}
	
	public WorkspaceModel getWorkspaceModel() {
		return workspaceModel;
	}

	public WorkspaceTree getWorkspaceTree() {
		return workspaceTree;
	}

	public DesktopPane getDesktop() {
		return desktop;
	}

	public StatusBar getStatusbar() {
		return statusbar;
	}

	public ArrayList<DiagramView> getDiagramViews() {
		return diagramViews;
	}

	public void setDiagramViews(ArrayList<DiagramView> diagramViews) {
		this.diagramViews = diagramViews;
	}

	public CommandActionManager getCommandActionManager() {
		return commandActionManager;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		JFrame frame = (JFrame) e.getComponent();
		int code = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to exit?",
				"Closing?", JOptionPane.YES_NO_OPTION);
		if (code != JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else {
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
