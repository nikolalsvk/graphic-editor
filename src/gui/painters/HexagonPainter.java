package gui.painters;

import java.awt.geom.GeneralPath;

import model.elements.DiagramElement;
import model.elements.HexagonElement;

public class HexagonPainter extends DevicePainter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2659106563618041153L;

	public HexagonPainter(DiagramElement element) {
		super(element);
		// TODO Auto-generated constructor stub

		HexagonElement hexagon = (HexagonElement) element;

		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(0, hexagon.getSize().getHeight() / 4);

		((GeneralPath) shape).lineTo(hexagon.getSize().width / 2, 0);

		((GeneralPath) shape).lineTo(hexagon.getSize().width, hexagon.getSize()
				.getHeight() / 4);

		((GeneralPath) shape).lineTo(hexagon.getSize().width, 3 * hexagon
				.getSize().getHeight() / 4);

		((GeneralPath) shape).lineTo(hexagon.getSize().width / 2, hexagon
				.getSize().getHeight());

		((GeneralPath) shape).lineTo(0, 3 * hexagon.getSize().getHeight() / 4);

		((GeneralPath) shape).closePath();
	}

}
