//package handlers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import logic.Plant;
//import logic.Player;

public class GardenButtonHandler implements EventHandler<ActionEvent> {

	private Player player;
	private Game game;

	public GardenButtonHandler(Player aPlayer, Game aGame) {
		this.player = aPlayer;
		this.game = aGame;
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
			game.placePlant("Sunflower", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			
		} else if (player.getPlantHeld().equals("PeaShooter")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("pea-shooter.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("PeaShooter", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			
		} else if (player.getPlantHeld().equals("Wallnut")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("walnut_full_life.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Wallnut", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);

		} else if (player.getPlantHeld().equals("CherryBomb")) {
			try {
				//Plant i = player.getPlantHeld();
				plant = new ImageView(new Image(new FileInputStream("cherry-bomb.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Cherry Bomb", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);

		} else if (player.getPlantHeld().equals("FrozenPeaShooter")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("frozen-pea.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Frozen PeaShooter", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
		}
		
		player.setPlantHeld("");
		source.setGraphic(plant);
		//ISSUE HERE: button is either completely transparent or can see button background too
		//source.setStyle("-fx-background-image: gardenslotsize.jpg;");
		source.setStyle("-fx-opacity: 0.5;");
		
		System.out.println(source.getText());
		System.out.println(Arrays.deepToString(game.getGardenPlots()).replace("}, ", "}\n").replace("{{", "{").replace("}}", "}"));
	}

}
