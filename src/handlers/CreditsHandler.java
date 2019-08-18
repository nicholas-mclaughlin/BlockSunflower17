package handlers;

import drivers.Session;
import gui.Credits;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * CreditsHandler is the button handler for the back buttons.
 * It creates a new scene of the desired destination/scene.
 *
 */
public class CreditsHandler implements EventHandler<ActionEvent> {
	
	/**
	 * gives access to session
	 */
	private Session session;
	
	public CreditsHandler(Session aSession) {
		this.session = aSession;
	}

	@Override
	public void handle(ActionEvent event) {
		/**
		 * Create the second scene and set it up
		 */
		Credits scene = new Credits(session);
		try {
			scene.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
