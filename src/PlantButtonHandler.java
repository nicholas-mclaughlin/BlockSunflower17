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
	
	//buys plant only if they player has sufficient funds, after which it decreases the money
	//also if they were able to buy the plant, it disables the button to show they bought it
	public void buyPlant() {
		if (player.getMoney() >= price) {
			player.decreaseMoney(price);
			player.setPlantHeld(plant);
			GardenScene.sunCounter.setText("  " + player.getMoney());
			disable = true;
		} else {
			//System.out.println("need more suns");
			disable = false;
		}
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
			plant = "Frozen PeaShooter";
			price = 175;
			buyPlant();
		} else if (source.getText().contains("p1")) {
			plant = "PeaShooter";
			price = 100;
			buyPlant();
		} else if (source.getText().contains("p2")) {
			plant = "Sunflower";
			price = 50;
			buyPlant();
		} else if (source.getText().contains("p3")) {
			plant = "Cherry Bomb";
			price = 150;
			buyPlant();
		} else if (source.getText().contains("p4")) {
			plant = "Wallnut";
			price = 50;
			buyPlant();
		}

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
