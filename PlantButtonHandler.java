//package handlers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.control.Button;
//import logic.Player;





public class PlantButtonHandler implements EventHandler<ActionEvent> {
	
	private Player player;

	public PlantButtonHandler(Player aPlayer) {
		this.player = aPlayer;
	}

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
