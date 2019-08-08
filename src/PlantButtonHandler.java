//package handlers;

import java.util.Timer;
import java.util.TimerTask;

//import gui.GardenScene;
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
	//This will set the price and the plant and whether to disable it or not
	private int price;
	private boolean disable;
	private String plant;

	public PlantButtonHandler(Player aPlayer) {
		this.player = aPlayer;
	}
	
	/**
	 * This method will set the plantHeld by the player depending on which plant button
	 * they click on.
	 * Player buys plant only if they have sufficient funds, after which it decreases the money.
	 * and disables the plant button clicked for a certain amount of time. 
	 */
	public void buyPlant() {
		if (player.getMoney() >= price) {
			player.decreaseMoney(price);
			player.setPlantHeld(plant);
			GardenScene.sunCounter.setText("  " + player.getMoney());
			disable = true;
		} else {
			disable = false;
		}
	}

	/**
	 * Override javafx default handler.
	 * Sets the button clicked to the correct plant and price.
	 * Calls buyPlant() to decrease the money and disable the plant if needed.
	 * @param ActionEvent	event is the case when a (plant) button is clicked by the user.
	 */
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
			plant = "Cherry Bomb";
			price = 150;
		} else if (source.getText().contains("p4")) {
			plant = "Wallnut";
			price = 50;
		}

		buyPlant();
		/**
		 * First plant button is disabled, only if the plant was bought, and timer sets
		 * the time for the later task, to enable the button again, to run after a time 0f 10000ms.
		 */
		if (disable) {
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
