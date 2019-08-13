package handlers;

import drivers.Session;
import gui.GardenScene;
import gui.Homepage;
import gui.Menu;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BackHandler implements EventHandler<ActionEvent> {
	
	private Session session;
	private String sceneType;

	public BackHandler(Session session, String sceneType) {
		this.session = session;
		this.sceneType = sceneType;
	}

	@Override
	public void handle(ActionEvent event) {
		//Create the second scene and set it up depending on which level was clicked
		if (sceneType.equals("Homepage")) {
			Homepage scene = new Homepage(session);
			try {
				scene.setup();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (sceneType.equals("Menu")) {
			Menu scene = new Menu(session);
			try {
				//GardenScene.root.pause();
				//Timeline anim = TimelineBuilder.create();
				
				scene.setup();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 

}
