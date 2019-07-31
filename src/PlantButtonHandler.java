//package handlers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.control.Button;
//import logic.Player;

/**
 * PlantButtonHandler is the button handler for the plant buttons.
 * It sets the plantHeld by the player depending on which plant button
 * is clicked.
 *
 */
public class PlantButtonHandler implements EventHandler<ActionEvent> {
	//This will give access to the game's player.
	private Player player;

	public PlantButtonHandler(Player aPlayer) {
		this.player = aPlayer;
	}

	/**
	 * Override javafx default handler.
	 * This method will set the plantHeld by the player depending on which plant button
	 * they click on.
	 * The plant button clicked will be disabled for a certain amount of time right after.
	 * 
	 * @param ActionEvent	event is the case when a (plant) button is clicked by the user.
	 */
	@Override
	public void handle(ActionEvent event){
	
		Button source = (Button) event.getSource();
		
		if (source.getText().contains("p0")) {
			player.setPlantHeld("Frozen PeaShooter");
		} else if (source.getText().contains("p1")) {
			player.setPlantHeld("PeaShooter");
		} else if (source.getText().contains("p2")) {
			player.setPlantHeld("Sunflower");
		} else if (source.getText().contains("p3")) {
			player.setPlantHeld("Cherry Bomb");
		} else if (source.getText().contains("p4")) {
			player.setPlantHeld("Wallnut");
		}

		/**
		 * First plant button is disabled and timer set the time for the later task,
		 * to enable the button again, to run after a time 0f 10000ms.
		 */
		source.setDisable(true);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	source.setDisable(false);
		                }
		            });

		        }
		    }, 10000);

	}
}
