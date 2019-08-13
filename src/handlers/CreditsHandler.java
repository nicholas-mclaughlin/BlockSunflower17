package handlers;

import drivers.Session;
import gui.Credits;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CreditsHandler implements EventHandler<ActionEvent> {
	
	private Session session;
	
	public CreditsHandler(Session aSession) {
		this.session = aSession;
	}

	@Override
	public void handle(ActionEvent event) {
		//Create the second scene and set it up
		Credits scene = new Credits(session);
		try {
			scene.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
