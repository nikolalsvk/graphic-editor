package action.command;


public class CommandActionManager {
	private UndoAction undoAction = new UndoAction();
	private RedoAction redoAction = new RedoAction();
	
	public UndoAction getUndoAction() {
		return undoAction;
	}
	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}
	public RedoAction getRedoAction() {
		return redoAction;
	}
	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}	
}
