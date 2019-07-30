//package handlers;
//import drivers.Session;
//import gui.GardenScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartButtonHandler implements EventHandler<ActionEvent> {
	Session session;
	
	public StartButtonHandler(Session session) {
		this.session = session;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		//Create the second scene and set it up
		GardenScene scene = new GardenScene(session);
		try {
			scene.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
