package handlers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import java.util.Timer;
import java.util.TimerTask;
import gui.GardenScene;
import logic.Plant;
import logic.Player;


public class SunButtonHandler implements EventHandler<ActionEvent> {
	
	private Player player;
	
	public SunButtonHandler(Player player) {
		this.player = player;
	}

	/*
	 * handler sets up the sun buttons (disabled and invisible by default).
	 * player increases the money by 25 when sun is clicked, the value of the suns.
	 * Creates the sun counter that shows on the scene how many suns a player have.
	 * sun counter is updated everytime a sun is clicked and then disables the sun
	 * on the sunflower and makes it invisible until 6 seconds later when the sun appears again
	 */
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		
		if (GardenScene.getGameOverMessage().getText().equals("")) {
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.0;");
			
			player.increaseMoney();
			
			Button sunCounter = null;
			try {
				sunCounter = GardenScene.getSunCounter(player);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sunCounter.setText("  " + player.getMoney());
			sunCounter.setStyle("-fx-background-image: url('/gui/pvzsun.png')");
			sunCounter.setPrefSize(170,  70);
			sunCounter.setFont(new Font("Arial Bold", 38));
			if (source.getText().equals("sunflower button") && Plant.isSunflowerStillAlive()) {
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


