//package handlers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import logic.Plant;
//import logic.Player;

public class GardenButtonHandler implements EventHandler<ActionEvent> {

	private Player player;

	public GardenButtonHandler(Player aPlayer) {
		this.player = aPlayer;
	}

	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		ImageView plant = null;
		System.out.println(player.getPlantHeld());
		if (player.getPlantHeld().equals("Sunflower")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("Sunflower.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (player.getPlantHeld().equals("PeaShooter")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("pea-shooter.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (player.getPlantHeld().equals("Wallnut")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("walnut_full_life.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (player.getPlantHeld().equals("CherryBomb")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("cherry-bomb.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (player.getPlantHeld().equals("FrozenPeaShooter")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("frozen-pea.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		source.setGraphic(plant);
		//ISSUE HERE: button is either completely transparent or can see button background too
		//source.setStyle("-fx-background-image: gardenslotsize.jpg;");
		source.setStyle("-fx-opacity: 0.5;");
		source.setDisable(true);
	}

}
