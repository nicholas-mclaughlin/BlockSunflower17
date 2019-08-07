


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
//import logic.Player;
//import gui.GardenScene;


public class SunButtonHandler implements EventHandler<ActionEvent> {
	
	private Player player;
	
	public SunButtonHandler(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		//disables the sun button and makes it invisible
		Button source = (Button) event.getSource();
		source.setDisable(true);
		source.setStyle("-fx-opacity: 0.0;");
		
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
		sunCounter.setText("  " + player.getMoney());
		sunCounter.setStyle("-fx-background-image: url('/characters/pvzsun.png')");
		sunCounter.setPrefSize(170,  70);
		sunCounter.setFont(new Font("Arial Bold", 38));
	}
	}


