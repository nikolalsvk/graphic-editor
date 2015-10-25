package gui.tree;

import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.tree.TreePath;

import model.elements.DiagramDevice;
import model.elements.DiagramElement;
import model.tree.Diagram;
import model.tree.Project;
import model.tree.Workspace;
import app.MainFrame;
import app.tools.ResizingImage;

import commands.CommandManager;

import event.UpdateEvent;
import event.UpdateListener;
import gui.painters.ElementPainter;

public class DiagramView extends JInternalFrame implements UpdateListener,
		InternalFrameListener, AdjustmentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707945945354028185L;

	static int openDiagramCount = 0;
	static int xOffset = 15;
	static int yOffset = 15;
	static int i = 0;

	// scrolling

	private JScrollBar sbVertical;
	private JScrollBar sbHorizontal;
	private int hScrollValue = 140;
	private int vScrollValue = 140;

	private Diagram diagram;

	private JPanel framework;

	private CommandManager commandManager = new CommandManager();

	private Rectangle2D selectionRectangle = new Rectangle2D.Double();

	// transformations

	double translateX = 0;
	double translateY = 0;
	double scaling = 1;
	final static double translateFactor = 10;
	final public static double scalingFactor = 1.2;

	private AffineTransform transformation = new AffineTransform();

	// handles

	public enum Handle {
		North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
	}

	static final int handleSize = 7;
	private Point lastPosition = null;

	public DiagramView() {
		super("", true, true, true, true);

		openDiagramCount++;
		if (openDiagramCount > i + 1) {
			xOffset = 15;
			yOffset = 15;
		}
		setLocation(openDiagramCount * xOffset, openDiagramCount * yOffset);
		setSize(300, 400);
		setVisible(true);
		setFrameIcon(ResizingImage.resizeImg(new ImageIcon(
				"img/linedpaper32.png"), 18, 18));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		int screenHeight = MainFrame.getInstance().getDesktop().getSize().height;
		int screenWidth = MainFrame.getInstance().getDesktop().getSize().width;

		if ((openDiagramCount * xOffset) > screenHeight
				|| (openDiagramCount * yOffset) > screenWidth) {
			i++;
			openDiagramCount = i;
			xOffset = xOffset + 15;
			yOffset = yOffset + 15;
		}

		// Framework

		framework = new Framework();
		framework.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		framework.setBackground(Color.BLACK);
		getContentPane().add(framework, BorderLayout.CENTER);

		DiagramController diagramController = new DiagramController();
		framework.addMouseListener(diagramController);
		framework.addMouseMotionListener(diagramController);
		framework.addMouseWheelListener(diagramController);

		// listener za selekciju

		addInternalFrameListener(this);

		// scrollBars

		sbHorizontal = new JScrollBar(JScrollBar.HORIZONTAL, 140, 20, 0, 300);
		sbVertical = new JScrollBar(JScrollBar.VERTICAL, 140, 20, 0, 300);

		sbHorizontal.addAdjustmentListener(this);
		sbVertical.addAdjustmentListener(this);

		this.add(sbHorizontal, BorderLayout.SOUTH);
		this.add(sbVertical, BorderLayout.EAST);
	}

	private class DiagramController extends MouseAdapter implements
			MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			lastPosition = e.getPoint();
			transformToUserSpace(lastPosition);

			diagram.getStateManager().getCurrentState().mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			diagram.getStateManager().getCurrentState().mouseReleased(e);
		}

		public void mouseDragged(MouseEvent e) {
			diagram.getStateManager().getCurrentState().mouseDragged(e);
			MainFrame
					.getInstance()
					.getStatusbar()
					.setStatus(
							diagram.getStateManager().getCurrentState()
									.toString());
		}

		public void mouseMoved(MouseEvent e) {
			Point p = e.getPoint();
			MainFrame
					.getInstance()
					.getStatusbar()
					.setPosition(
							"x: " + (int) p.getX() + " y: " + (int) p.getY());

			MainFrame
					.getInstance()
					.getStatusbar()
					.setStatus(
							diagram.getStateManager().getCurrentState()
									.toString());

			diagram.getStateManager().getCurrentState().mouseMoved(e);
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
			if ((e.getModifiers() & MouseWheelEvent.CTRL_MASK) != 0) {
				double newScaling = scaling;
				if (e.getWheelRotation() > 0)
					newScaling *= (double) e.getWheelRotation() * scalingFactor;
				else
					newScaling /= -(double) e.getWheelRotation()
							* scalingFactor;

				if (newScaling < 0.2)
					newScaling = 0.2;
				if (newScaling > 5)
					newScaling = 5;

				Point oldPosition = e.getPoint();
				transformToUserSpace(oldPosition);

				scaling = newScaling;
				setupTransformation();

				Point newPosition = e.getPoint();
				transformToUserSpace(newPosition);

				translateX += newPosition.getX() - oldPosition.getX();
				translateY += newPosition.getY() - oldPosition.getY();

				sbVertical.setVisibleAmount((int) (100 / scaling));
				sbHorizontal.setVisibleAmount((int) (100 / scaling));

				setupTransformation();

			} else if ((e.getModifiers() & MouseWheelEvent.SHIFT_MASK) != 0) {
				//translateX += (double) e.getWheelRotation() * translateFactor
				//		/ scaling;
				// sbHorizontal.setValue(sbHorizontal.getValue() +
				// e.getWheelRotation());
				sbHorizontal.setValue(sbHorizontal.getValue() + e.getWheelRotation() * (int)(translateFactor/scaling));
			} else {
				//translateY += (double) e.getWheelRotation() * translateFactor
				//		/ scaling;
				// sbVertical.setValue(sbVertical.getValue() +
				// e.getWheelRotation());
				sbVertical.setValue(sbVertical.getValue() + e.getWheelRotation() * (int)(translateFactor/scaling));
			}

			setupTransformation();
			repaint();
		}
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
		this.setName(diagram.getName());
		this.diagram.getModel().addUpdateListener(this);
		this.diagram.getSelectionModel().addUpdateListener(this);
		setTitle(diagram.getName());
	}

	public void setNameEdited(String name) {
		this.setName(name);
		setTitle(name);
	}

	public String getProjectName() {
		return ((Project) diagram.getParent()).getName();
	}

	private class Framework extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7721105789494759053L;

		public Framework() {
			this.setBackground(Color.BLACK);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponents(g);

			Graphics2D g2 = (Graphics2D) g;

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.8f));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g2.transform(transformation);

			java.util.Iterator<DiagramDevice> it = diagram.getModel()
					.getDeviceIterator();

			while (it.hasNext()) {
				DiagramDevice d = (DiagramDevice) it.next();
				ElementPainter painter = d.getPainter();
				painter.paint(g2, d);
			}

			paintSelectionHandles(g2);
			//

			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_BEVEL, 1f, new float[] { (float) 3,
							(float) 6 }, 0));
			g2.draw(selectionRectangle);
		}
	}

	// transformation methods

	public void transformToUserSpace(Point2D deviceSpace) {
		try {
			transformation.inverseTransform(deviceSpace, deviceSpace);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupTransformation() {
		transformation.setToIdentity();
		// Zumiranje
		transformation.scale(scaling, scaling);
		// Skrolovanje
		transformation.translate(translateX, translateY);
	}

	private void paintSelectionHandles(Graphics2D g2) {
		Iterator<DiagramElement> it = diagram.getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			DiagramDevice device = (DiagramDevice) element;
			g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_BEVEL, 1f, new float[] { 3f, 6f }, 0));
			g2.setPaint(Color.BLACK);

			int xPoint = (int) (device.getPosition().getX() - (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);
			int yPoint = (int) (device.getPosition().getY() + (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);

			if (device.isRotated()) {
				g2.drawRect(xPoint, yPoint, (int) device.getSize().getHeight(),
						(int) device.getSize().getWidth());

				for (Handle e : Handle.values()) {
					paintSelectionHandle(
							g2,
							getHandlePoint(new Point(xPoint, yPoint),
									new Dimension((int) device.getSize()
											.getHeight(), (int) device
											.getSize().getWidth()), e));
				}
			} else {
				g2.drawRect((int) device.getPosition().getX(), (int) device
						.getPosition().getY(), (int) device.getSize()
						.getWidth(), (int) device.getSize().getHeight());

				for (Handle e : Handle.values()) {
					paintSelectionHandle(
							g2,
							getHandlePoint(device.getPosition(),
									device.getSize(), e));
				}
			}

		}
	}

	private void paintSelectionHandle(Graphics2D g2, Point2D position) {
		double size = handleSize;
		g2.fill(new Rectangle2D.Double(position.getX() - size / 2, position
				.getY() - size / 2, size, size));
	}

	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size,
			Handle handlePosition) {
		double x = 0, y = 0;

		// Ako su gornji hendlovi
		if (handlePosition == Handle.NorthWest
				|| handlePosition == Handle.North
				|| handlePosition == Handle.NorthEast) {
			y = topLeft.getY();
		}
		// Ako su centralni po y osi
		if (handlePosition == Handle.East || handlePosition == Handle.West) {
			y = topLeft.getY() + size.getHeight() / 2;
		}
		// Ako su donji
		if (handlePosition == Handle.SouthWest
				|| handlePosition == Handle.South
				|| handlePosition == Handle.SouthEast) {
			y = topLeft.getY() + size.getHeight();
		}

		// Ako su levi
		if (handlePosition == Handle.NorthWest || handlePosition == Handle.West
				|| handlePosition == Handle.SouthWest) {
			x = topLeft.getX();
		}
		// ako su centralni po x osi
		if (handlePosition == Handle.North || handlePosition == Handle.South) {
			x = topLeft.getX() + size.getWidth() / 2;
		}
		// ako su desni
		if (handlePosition == Handle.NorthEast || handlePosition == Handle.East
				|| handlePosition == Handle.SouthEast) {
			x = topLeft.getX() + size.getWidth();
		}

		return new Point2D.Double(x, y);

	}

	public void setMouseCursor(Point2D point) {

		Handle handle = getDeviceAndHandleForPoint(point);

		if (handle != null) {
			Cursor cursor = null;

			switch (handle) {
			case North:
				cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
				break;
			case South:
				cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
				break;
			case East:
				cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
				break;
			case West:
				cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
				break;
			case SouthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case NorthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case SouthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case NorthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				break;
			}
			framework.setCursor(cursor);
			MainFrame.getInstance().setCursor(cursor);
		} else {
			framework.setCursor(Cursor.getDefaultCursor());
			MainFrame.getInstance().setCursor(Cursor.getDefaultCursor());
		}
	}

	public Handle getDeviceAndHandleForPoint(Point2D point) {
		DiagramElement element;

		Iterator<DiagramElement> it = diagram.getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			element = it.next();
			return getHandleForPoint(element, point);
		}
		return null;
	}

	private Handle getHandleForPoint(DiagramElement element, Point2D point) {
		for (Handle h : Handle.values()) {
			if (isPointInHandle(element, point, h)) {
				return h;
			}
		}
		return null;
	}

	private boolean isPointInHandle(DiagramElement element, Point2D point,
			Handle handle) {
		DiagramDevice device = (DiagramDevice) element;
		Point2D handleCenter = null;
		if (device.isRotated()) {
			Dimension rotatedSize = new Dimension((int) device.getSize()
					.getHeight(), (int) device.getSize().getWidth());
			int xPoint = (int) (device.getPosition().getX() - (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);
			int yPoint = (int) (device.getPosition().getY() + (device.getSize()
					.getHeight() - device.getSize().getWidth()) / 2);

			handleCenter = getHandlePoint(new Point(xPoint, yPoint),
					rotatedSize, handle);
		} else {
			handleCenter = getHandlePoint(device.getPosition(),
					device.getSize(), handle);
		}
		return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize / 2) && (Math
				.abs(point.getY() - handleCenter.getY()) <= (double) handleSize / 2));
	}

	public Point getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Rectangle2D getSelectionRectangle() {
		return selectionRectangle;
	}

	public void setSelectionRectangle(Rectangle2D selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		// Also it's magic!

		TreePath path = MainFrame.getInstance().getWorkspaceTree()
				.getSelectionPath();
		int currentSelectedInTree = 0;

		if (path == null
				|| (MainFrame.getInstance().getWorkspaceTree()
						.getLastSelectedPathComponent() instanceof Project)) {
			Object[] pomAL = new Object[3];
			pomAL[0] = ((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot());
			pomAL[1] = (diagram.getParent());
			pomAL[2] = (getDiagram());
			TreePath pathSelect = new TreePath(pomAL);

			MainFrame.getInstance().getWorkspaceTree()
					.setSelectionPath(pathSelect);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		} else {
			for (int i = 0; i < path.getPathCount(); i++) {
				if (path.getPathComponent(i) instanceof Diagram) {

					Diagram d = (Diagram) path.getPathComponent(i);
					JInternalFrame[] frame = MainFrame.getInstance()
							.getDesktop().getAllFrames();

					for (int j = 0; j < MainFrame.getInstance().getDesktop()
							.getAllFrames().length; j++) {
						if (d.getName().equals(frame[j].getName()))
							currentSelectedInTree = j;
					}
				}
			}
		}

		if (MainFrame
				.getInstance()
				.getDesktop()
				.getSelectedFrame()
				.equals(MainFrame.getInstance().getDesktop().getAllFrames()[currentSelectedInTree])) {
			// System.out.println("Test");
			return;
		}

		Object[] pomAL = new Object[3];
		pomAL[0] = ((Workspace) MainFrame.getInstance().getWorkspaceModel()
				.getRoot());
		pomAL[1] = (diagram.getParent());
		pomAL[2] = (getDiagram());
		TreePath pathSelect = new TreePath(pomAL);
		if (MainFrame.getInstance().getWorkspaceTree().getSelectionPath() != pathSelect) {
			MainFrame.getInstance().getWorkspaceTree()
					.setSelectionPath(pathSelect);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		}

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Closed");
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Closing");
		MainFrame
				.getInstance()
				.getWorkspaceTree()
				.removeDiagramViewsFromList(
						MainFrame.getInstance().getWorkspaceTree()
								.getDiagramIndex(this));
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		/*
		// Nakon odredjivanja vrednosti translateX i translateY koje
		// zavise potrebno je setovati novu transformaciju
		// uzeti u obzir koji je skrol bar pomeran
		// i u zavisnosti od prethodne pozicije datog skrol bara
		// i trenutnog skaliranja izvrsiti transformaciju translacije
		// nove
		if (e.getAdjustable().getOrientation() == Adjustable.HORIZONTAL) {
			if (hScrollValue < e.getValue()) {
				translateX += (double) ((e.getValue() - hScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
				// transformation.translate((double)((e.getValue()-hScrollValue)*(-translateFactor))/transformation.getScaleX(),
				// 0);

			} else {
				translateX += (double) ((e.getValue() - hScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
				// translateX+=(double)((hScrollValue-e.getValue())*(-translateFactor))/transformation.getScaleX();
				// transformation.translate((double)((hScrollValue-e.getValue())*(translateFactor))/transformation.getScaleX(),
				// 0);
			}
			hScrollValue = e.getValue();

		} else {
			if (vScrollValue < e.getValue()) {
				translateY += (double) ((e.getValue() - vScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
				// transformation.translate(0,(double)((e.getValue()-vScrollValue)*(-translateFactor))/transformation.getScaleX());
			} else {
				translateY += (double) ((e.getValue() - vScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
				// translateY+=(double)((vScrollValue-e.getValue())*(-translateFactor))/transformation.getScaleX();
				// transformation.translate(0,(double)((vScrollValue-e.getValue())*(translateFactor))/transformation.getScaleX());
			}
			vScrollValue = e.getValue();
		}
		*/
		if (e.getAdjustable().getOrientation()==Adjustable.HORIZONTAL){
			translateX -=  e.getValue() - hScrollValue;
			hScrollValue  = e.getValue();
		}
		else {
			translateY -=  e.getValue()-vScrollValue;
			vScrollValue = e.getValue();
		}
		
		setupTransformation();
		repaint();
	}

	public void zoomIn() {
		double newScaling = scaling;

		newScaling *= scalingFactor;

		// Zatim je potrebno da skaliranje održimo u intervalu [0.2, 5]
		if (newScaling < 0.2)
			newScaling = 0.2;
		if (newScaling > 5)
			newScaling = 5;

		Point oldPosition = new Point(getWidth() / 2, getHeight() / 2);
		transformToUserSpace(oldPosition);

		scaling = newScaling;
		setupTransformation();

		Point newPosition = new Point(getWidth() / 2, getHeight() / 2);
		transformToUserSpace(newPosition);

		translateX += newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();

		sbVertical.setVisibleAmount((int) (20 / scaling));
		sbHorizontal.setVisibleAmount((int) (20 / scaling));

		setupTransformation();
	}

	public void zoomOut() {
		double newScaling = scaling;

		newScaling /= scalingFactor;

		// Zatim je potrebno da skaliranje održimo u intervalu [0.2, 5]
		if (newScaling < 0.2)
			newScaling = 0.2;
		if (newScaling > 5)
			newScaling = 5;

		/*
		 * newScaling je novi parametar skaliranja (članovi m00 i m11
		 * transformacione matrice) Prilikom skaliranja dolazi do pomeranja
		 * userspace koordinata na kojima se nalazi pokazivač miša. Da bi dobili
		 * ispravan Point zoom moramo izvršiti translaciju tako da poništimo
		 * "smicanje" usled skaliranja tj. moramo postići da se userspace
		 * koordinate miša ne promene.
		 */

		Point2D oldPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(oldPosition);

		scaling = newScaling;
		setupTransformation();

		Point2D newPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(newPosition);

		translateX += newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();

		sbVertical.setVisibleAmount((int) (20 / scaling));
		sbHorizontal.setVisibleAmount((int) (20 / scaling));

		setupTransformation();
	}
}
