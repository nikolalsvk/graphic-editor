package action;


public class ActionManager {
 
	private NewProjectAction newProjectAction = new NewProjectAction();
	
	public NewProjectAction getNewDiagramAction() {
		return newProjectAction;
	}
}
