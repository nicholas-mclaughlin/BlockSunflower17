

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
//import logic.Player;
//import logic.Sun;

import java.util.Timer;
import java.util.TimerTask;

//import gui.GardenScene;
//import handlers.GardenButtonHandler;


public class SunflowerSunHandler implements EventHandler<ActionEvent> {
	
	private Player player;
	
	public SunflowerSunHandler(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		//disables the sun button and makes it invisible
		Button source = (Button) event.getSource();		
		
		//increases the money by 25, the value of the suns
		player.increaseMoney();
		
		//creates the button 
		Button sunCounter = null;
		try {
			sunCounter = GardenScene.getSunCounter(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sets the button style and increases the money
		sunCounter.setText("Suns: " + player.getMoney());
		sunCounter.setStyle("-fx-background-image: url('/characters/sun.png')");
		sunCounter.setPrefSize(170,  87);
		sunCounter.setFont(new Font(20));
		
		source.setDisable(true);
		source.setStyle("-fx-opacity: 0.0;");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                	source.setDisable(false);
		                	source.setStyle("-fx-background-color: transparent;");
		                }
		            });

		        }
		    }, 6000);
		
	}

}
