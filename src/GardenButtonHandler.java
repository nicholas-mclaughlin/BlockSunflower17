//package handlers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

//import gui.GardenScene;
//import handlers.SunflowerSunHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import logic.Plant;
//import logic.Player;
//import logic.Sun;

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
	public double xPosition;
	public double yPosition;
	private Button sunButton;
	private ImageView peaBullet;
	private ImageView frozenBullet;

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
		ImageView plantImage = null;
		Plant plant = new Plant(player.getPlantHeld());
		plant.setRow(this.game, source.getText());
		plant.setColumn(this.game, source.getText());
		System.out.println(player.getPlantHeld());
		//getting button clicked position to know where to place suns or peas 
		xPosition = source.getLayoutX();
		yPosition = source.getLayoutY();
		
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
		if (plant.getType().equals("Sunflower")) {
			try {
				plantImage = new ImageView(new Image(new FileInputStream("Sunflower.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);
			
			//placing the sunButton
			sunButton = null;
			try {
				sunButton = new Sun().getSunButton();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sunButton.setOnAction(new SunflowerSunHandler(player));
			//positioning the sun button on the sunflower
			sunButton.setLayoutX(xPosition);
			sunButton.setLayoutY(yPosition + 150);
			//to add the sunButton after 6000 milliseconds
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	GardenScene.fullImage.getChildren().add(sunButton);	
			                	//code to move the bullet goes here
			                }
			            });

			        }
			    }, 6000);
				
		} else if (plant.getType().equals("PeaShooter")) {
			try {
				//Plant i = player.getPlantHeld();
				plantImage = new ImageView(new Image(new FileInputStream("pea-shooter.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);
			
			//creating pea bullet image
			try {
				peaBullet = new ImageView(new Image( new FileInputStream("pea-bullet.png")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			peaBullet.setLayoutX(xPosition + 53);
			peaBullet.setLayoutY(yPosition + 165);
			//making bullet appear afterwards every 1000 milliseconds
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			        			GardenScene.fullImage.getChildren().add(peaBullet);	
			        			//code to move the bullet goes here 
			                }
			            });

			        }
			    }, 1000);	

		} else if (plant.getType().equals("Wallnut")) {
			try {
				//Plant i = player.getPlantHeld();
				plantImage = new ImageView(new Image(new FileInputStream("walnut_full_life.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

		} else if (plant.getType().equals("Cherry Bomb")) {
			try {
				//Plant i = player.getPlantHeld();
				plantImage = new ImageView(new Image(new FileInputStream("cherry-bomb.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

		} else if (plant.getType().equals("Frozen PeaShooter")) {
			try {
				//Plant i = player.getPlantHeld();
				plantImage = new ImageView(new Image(new FileInputStream("frozen-pea.gif")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);
			
			//creating frozen pea bullet image
			try {
				frozenBullet = new ImageView(new Image( new FileInputStream("frozen-pea-bullet.png")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			frozenBullet.setLayoutX(xPosition + 53);
			frozenBullet.setLayoutY(yPosition + 165);
			//making bullet appear afterwards every 1000 milliseconds
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	//peaBullet.setStyle("-fx-background-color: transparent;");
			        			GardenScene.fullImage.getChildren().add(frozenBullet);	
			                }
			            });

			        }
			    }, 1000);	
			
		}
		
		player.setPlantHeld("");
		source.setGraphic(plantImage);
		//ISSUE HERE: button is either completely transparent or can see button background too
		//source.setStyle("-fx-opacity: 0.5;");

		System.out.println(source.getText());
		System.out.println(Arrays.deepToString(game.gardenPlotString()));
	}
}
