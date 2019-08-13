package handlers;
import drivers.Session;
import gui.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayHandler implements EventHandler<ActionEvent> {
	//This gives access to the session.
	private Session session;
	
	public PlayHandler(Session session) {
		this.session = session;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
		//Create the second scene and set it up depending on which level was clicked
		Menu scene = new Menu(session);
		try {
			scene.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
