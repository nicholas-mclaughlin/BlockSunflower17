package handlers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.util.Timer;
import java.util.TimerTask;

import gui.GardenScene;
import logic.Player;


public class SunButtonHandler implements EventHandler<ActionEvent> {
	
	private Player player;
	
	public SunButtonHandler(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		Button source = (Button) event.getSource();
		
		if (GardenScene.gameOverMessage.getText().equals("")) {
			//disables the sun button and makes it invisible
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.0;");
			
			//increases the money by 25, the value of the suns
			player.increaseMoney();
			
			//creates the sun counter 
			Button sunCounter = null;
			try {
				sunCounter = GardenScene.getSunCounter(player);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//sets the sun counter style and increases the money
			sunCounter.setText("  " + player.getMoney());
			sunCounter.setStyle("-fx-background-image: url('/gui/pvzsun.png')");
			sunCounter.setPrefSize(170,  70);
			sunCounter.setFont(new Font("Arial Bold", 38));
			if (source.getText().equals("sunflower button")){
				//disables the sun on the sunflower and makes it invisible until 6 seconds later when the sun appears again
				source.setDisable(true);
				source.setStyle("-fx-opacity: 0.0;");
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
				        @Override
				        public void run() {
				            Platform.runLater(new Runnable() {
				                @Override
				                public void run() {
				                	//the sun is no longer disabled and as originally created, the background of the sun button is transparent
				                	source.setDisable(false);
				                	source.setStyle("-fx-background-color: transparent;");
				                }
				            });
	
				        }
				    }, 6000);
			}
		}
	}
	
}


