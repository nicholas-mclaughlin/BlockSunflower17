package handlers;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.control.Button;

import logic.Player;
import gui.GardenScene;

// PlantButtonHandler is the button handler for the plant buttons.
public class PlantButtonHandler implements EventHandler<ActionEvent> {
	
	//This will give access to the game's player. 
	private Player player;
	private int price;
	private boolean disable;
	private String plant;

	public PlantButtonHandler(Player aPlayer) {
		this.player = aPlayer;
	}
	
	//buys plant only if they player has sufficient funds, after which it decreases the money
	//also if they were able to buy the plant, it disables the button to show they bought it
	public void buyPlant() {
		if (player.getMoney() >= price) {
			player.decreaseMoney(price);
			player.setPlantHeld(plant); //sets plantHeld by player to plant bought
			GardenScene.sunCounter.setText("  " + player.getMoney()); //changes sun amount
			disable = true;
		} else {
			disable = false;
		}
	}

	
	@Override
	public void handle(ActionEvent event){
		 
		Button source = (Button) event.getSource();
		
		if (source.getText().contains("p0")) {
			plant = "Frozen PeaShooter";
			price = 175;
		} else if (source.getText().contains("p1")) {
			plant = "PeaShooter";
			price = 100;
		} else if (source.getText().contains("p2")) {
			plant = "Sunflower";
			price = 50;
		} else if (source.getText().contains("p3")) {
			plant = "Potato Mine";
			price = 25;
		} else if (source.getText().contains("p4")) {
			plant = "Wallnut";
			price = 50;
		}

		buyPlant();
		
		/**
		 * First plant button is disabled, only if the plant was bought, and timer sets
		 * the time for the later task, to enable the button again, to run after a time 0f 10000ms.
		 * The error message also appears since a plant was bought.
		 */
		if (disable) {
			if (GardenButtonHandler.errorMessage != null) {
				 GardenButtonHandler.errorMessage.setDisable(true);
				 GardenButtonHandler.errorMessage.setStyle("-fx-opacity: 0.0;");
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
			    }, 8000);
		}

	}
}
