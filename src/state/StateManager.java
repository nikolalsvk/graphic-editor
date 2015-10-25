package state;

import java.io.Serializable;

import model.tree.Diagram;

public class StateManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7229965144996771305L;

	private State currentState;
	
	RectangleState rectangleState;
	HexagonState hexagonState;
	SelectState selectState;
	CircleState circleState;
	TriangleState triangleState;
	LassoState lassoState;
	MoveState moveState;
	ResizeState resizeState;
	
	public StateManager(Diagram diagram) {
		rectangleState = new RectangleState(diagram);
		hexagonState = new HexagonState(diagram);
		circleState = new CircleState(diagram);
		triangleState = new TriangleState(diagram);
		selectState = new SelectState(diagram);
		lassoState = new LassoState(diagram);
		moveState = new MoveState(diagram);
		resizeState = new ResizeState(diagram);
		currentState = selectState;
	}

	public void setRectangleState() {
		currentState = rectangleState;
	}
	
	public void setHexagonState() {
		currentState = hexagonState;
	}

	public void setSelectState() {
		currentState = selectState;
	}
	
	public void setCircleState() {
		currentState = circleState;
	}
	
	public void setTriangleState() {
		currentState = triangleState;
	}

	public void setLassoState() {
		currentState = lassoState;
	}
	
	public void setMoveState() {
		currentState = moveState;
	}
	
	public void setResizeState() {
		currentState = resizeState;
	}

	public State getCurrentState() {
		return currentState;
	}
}
