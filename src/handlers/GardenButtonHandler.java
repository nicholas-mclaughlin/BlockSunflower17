package handlers;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import gui.GardenScene;
import handlers.SunButtonHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import logic.*;


/**
 * GardenButtonHandler is the button handler for the garden plot buttons.
 * Changes to the game's garden plot logic will also be done here depending on the player's moves.
 *
 */
public class GardenButtonHandler implements EventHandler<ActionEvent> {

	/**
	 * player gives access to the session's game and the game's player.
	 * sunButton creates the sunflower sun button and the bullets so they are accessible inside the timers.
	 */
	private Player player;
	private Game game;
	private Button sunButton;


	public GardenButtonHandler(Player aPlayer, Game aGame) {
		this.player = aPlayer;
		this.game = aGame;
	}


	@SuppressWarnings("static-access")
	@Override
	public void handle(ActionEvent event) {

		Button source = (Button) event.getSource();
		Plant plant = null;
		try {
			plant = new Plant(player.getPlantHeld());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			plant.setRow(this.game, source.getText());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			plant.setColumn(this.game, source.getText());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println(player.getPlantHeld());
		plant.setxPosition(source.getLayoutX() + 25);
		plant.setyPosition(source.getLayoutY() + 160);
		plant.setBulletXPosition(source.getLayoutX() + 65);
		plant.setBulletYPosition(source.getLayoutY() + 165);
		plant.setBulletStartPosition(source.getLayoutX() + 65);


		/**
		 * By calling for the (type String) plantheld by the player and comparing it
		 * with an existing type of plant in the game (type String), appropriate image will be placed
		 * on the appropriate coordinate in the garden button.
		 *
		 * Row and column are determined by using the (invisible) text on the buttons and comparing
		 * its string to a similar string in the 2D array list of the garden plot coordinates.
		 *
		 */
		if (plant.getType().equals("Sunflower")) {

			try {
				game.placePlant(plant, plant.getRow(), plant.getColumn());
			} catch (Exception e1) {
				e1.printStackTrace();
			}


			/**
			 * places the sun gifs which is a sunButton.
			 * positions the sun button on the sunflower and
			 * adds the sunButton after 6000 milliseconds
			 */
			sunButton = null;
			try {
				sunButton = new Plant("").getSunButton();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sunButton.setText("sunflower button");
			sunButton.setFont(new Font(0));
			sunButton.setOnAction(new SunButtonHandler(player));
			sunButton.setLayoutX(plant.getxPosition() - 25);
			sunButton.setLayoutY(plant.getyPosition() - 10);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	GardenScene.getFullImage().getChildren().add(sunButton);
			                }
			            });

			        }
			    }, 6000);

		}
		
		else if (plant.getType().equals("PeaShooter")) {
			try {
				game.placePlant(plant, plant.getRow(), plant.getColumn());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		else if (plant.getType().equals("Wallnut")) {
			try {
				game.placePlant(plant, plant.getRow(), plant.getColumn());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (plant.getType().equals("Potato Mine")) {
			try {
				game.placePlant(plant, plant.getRow(), plant.getColumn());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (plant.getType().equals("Frozen PeaShooter")) {
			try {
				game.placePlant(plant, plant.getRow(), plant.getColumn());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		/**
		 * if there was no plant being held, it was set to blank thus the error message is set to being seen
		 */
		else if (player.getPlantHeld().equals("")){
			
			GardenScene.getErrorMessage().setStyle("-fx-font-size: 50; -fx-background-color: transparent; -fx-font-weight: bold;");
			GardenScene.getErrorMessage().setLayoutX(710);
			GardenScene.getErrorMessage().setLayoutY(3);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	GardenScene.getErrorMessage().setStyle("-fx-opacity: 0.0;");
			                }
			            });
			        }
			    }, 2500);
		}
		
		
		
		player.setPlantHeld("");
		System.out.println(source.getText());
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        try {
					System.out.print(Game.getTheGarden()[i][j] + " ");
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    System.out.println();
		}

	}}
