package handlers;

import gui.GardenScene;
import drivers.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *  Levelhandler is the button handler for the first scene's button's.
 *  Creates the second scene and set it up depending on which level was clicked.
 */
public class LevelHandler implements EventHandler<ActionEvent> {
	
	/**
	 * This gives access to the session.
	 */
	private Session session;
	private int levelNum;
	
	public LevelHandler(Session session, int levelNum) {
		this.session = session;
		this.levelNum = levelNum;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		GardenScene scene = new GardenScene(session, levelNum);
		try {
			scene.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
