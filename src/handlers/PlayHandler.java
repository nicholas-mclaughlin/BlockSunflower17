package handlers;
import drivers.Session;
import gui.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Creates the second scene (Menu Scene) and set it up.
 */
public class PlayHandler implements EventHandler<ActionEvent> {
	/**
	 * This gives access to the session.
	 */
	private Session session;
	
	public PlayHandler(Session session) {
		this.session = session;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
		Menu scene = new Menu(session);
		try {
			scene.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
