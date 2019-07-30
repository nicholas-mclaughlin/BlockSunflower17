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

/**
 * GardenButtonHandler is the button handler for the garden plot buttons.
 * Other than setting the proper images to the garden scene,
 * changes to the game's garden plot logic will be done here
 * depending on the player's moves.
 *
 */
public class GardenButtonHandler implements EventHandler<ActionEvent> {

	//Give access to the session's game and the game's player.
	private Player player;
	private Game game;

	//constructor
	public GardenButtonHandler(Player aPlayer, Game aGame) {
		this.player = aPlayer;
		this.game = aGame;
	}

	/**
	 * Override javafx default handler.
	 * This method will visually place a plant onto a garden plot if the player has
	 * clicked a plant prior to clicking a garden plot.
	 * Logically, it will place the plant as well into the kept 2D array of the gardenplot
	 * in order to keep track of what has been planted in which coordinate.
	 * It replaces the player's plantHeld back to default ("") at the end of the method.
	 * 
	 * @param ActionEvent	event is the case when a (garden plot) button is clicked by the user.
	 */
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		ImageView plant = null;
		System.out.println(player.getPlantHeld());
		
		/**
		 * By calling for the (type String) plantheld by the player and comparing it
		 * with an existing type of plant in the game (type String), appropriate image will be placed
		 * on the garden button.
		 * 
		 * The game keeps track of the player's move by replacing the string element of coordinate in
		 * the default String 2D array with the (String) type of the plant.
		 * Row and column are determined by using the (invisible) text on the buttons and comparing
		 * its string to a similar string in the 2D array list of the garden plot coordinates.
		 * 
		 * Buttons are disabled once a plant has been placed on it.
		 * Buttons are also set to an opacity of 0.5 to be better seen.
		 */
		if (player.getPlantHeld().equals("Sunflower")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("Sunflower.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Sunflower", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.5;");
			
		} else if (player.getPlantHeld().equals("PeaShooter")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("pea-shooter.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("PeaShooter", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.5;");
			
		} else if (player.getPlantHeld().equals("Wallnut")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("walnut_full_life.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Wallnut", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.5;");

		} else if (player.getPlantHeld().equals("Cherry Bomb")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("cherry-bomb.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Cherry Bomb", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.5;");

		} else if (player.getPlantHeld().equals("Frozen PeaShooter")) {
			try {
				plant = new ImageView(new Image(new FileInputStream("frozen-pea.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant("Frozen PeaShooter", game.getRow(source.getText()), game.getColumn(source.getText()));
			source.setDisable(true);
			source.setStyle("-fx-opacity: 0.5;");
			
		} else {
			source.setDisable(false);
		}
		player.setPlantHeld("");
		
		source.setGraphic(plant);		
		
		System.out.println(source.getText());
		System.out.println(Arrays.deepToString(game.getGardenPlots()).replace("}, ", "}\n").replace("{{", "{").replace("}}", "}"));
	}

}
