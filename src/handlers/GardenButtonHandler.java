package handlers;
//package handlers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import gui.GardenScene;
import javafx.animation.TranslateTransition;
//import gui.GardenScene;
import handlers.SunButtonHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.Game;
import logic.Plant;
import logic.Player;
import logic.Sun;
import logic.Zombie;


/**
 * GardenButtonHandler is the button handler for the garden plot buttons.
 * Changes to the game's garden plot logic will also be done here depending on the player's moves.
 *
 */
public class GardenButtonHandler implements EventHandler<ActionEvent> {

	//Give access to the session's game and the game's player.
	private Player player;
	private Game game;
	//Creates the positions of the button that is clicked.
	public double xPosition;
	public double yPosition;
	//Creates the sunflower sun button and the bullets so they are accessible inside the timers.
	private Button sunButton;
	private ImageView bullet;


	ImageView plantImage = null;
	private Rectangle bulletRect;


	//public static Button errorMessage = new Button("Buy a plant first!");

	public Rectangle getBounds(ImageView z) {
		return new Rectangle( z.getLayoutX(), z.getLayoutY(), 25, 25);
	}

	//constructor
	public GardenButtonHandler(Player aPlayer, Game aGame) {
		this.player = aPlayer;
		this.game = aGame;
	}


	/*public void trackBullet() {
		int intervals = 100;
		double startPoint = xPosition - 250;
		int numOfMoves = 3000/intervals;
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		            @Override
		             public void run() {

		            	xPosition2 += startPoint/numOfMoves;
		            	System.out.println(xPosition2);
		             }
		 }, 0, intervals);
	} */

	/**
	 * Handle() will visually and logically place a plant onto a garden plot if the player has clicked a plant prior
	 * to clicking a garden plot. It replaces the player's plantHeld back to default ("") at the end of the method.
	 *
	 * @param ActionEvent	event is the case when a (garden plot) button is clicked by the user.
	 */
	@Override
	public void handle(ActionEvent event) {

		Button source = (Button) event.getSource();

		//ImageView plantImage = null;
		Plant plant = null;
		try {
			plant = new Plant(player.getPlantHeld());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		plant.setRow(this.game, source.getText());
		plant.setColumn(this.game, source.getText());
		System.out.println(player.getPlantHeld());
		//getting button clicked position to know where to place suns or peas
		xPosition = source.getLayoutX();
		yPosition = source.getLayoutY();

		int bulletEndPosition = (int) (1125 - xPosition);


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

			game.placePlant(plant, plant.getRow(), plant.getColumn());


			//placing the sun gifs which is a sunButton
			sunButton = null;
			try {
				sunButton = new Sun().getSunButton();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sunButton.setText("sunflower button");
			sunButton.setFont(new Font(0));
			sunButton.setOnAction(new SunButtonHandler(player));
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
			                }
			            });

			        }
			    }, 6000);

		} else if (plant.getType().equals("PeaShooter")) {

			game.placePlant(plant, plant.getRow(), plant.getColumn());


			//creating pea bullet image
			try {
				bullet = new ImageView(new Image( new FileInputStream("PlantImages//pea-bullet.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//Setting the position of the pea bullet so it starts at the peashooter's mouth.
			bullet.setLayoutX(xPosition + 60 );
			bullet.setLayoutY(yPosition + 165);


			bulletRect = getBounds(bullet);
			bulletRect.setFill(Color.TRANSPARENT);
			bulletRect.setStroke(Color.BLACK);
		    bulletRect.setStrokeWidth(2);
			          	      TranslateTransition translateTransition = new TranslateTransition();
			          	      //How long the animation will take
			          	      translateTransition.setDuration(Duration.millis(3000));
			          	      translateTransition.setNode(bullet);
			          	      //The displacement of the animation
			          	      translateTransition.setByX(1125 - xPosition);
			          	      translateTransition.setCycleCount(1000);
			          	      translateTransition.setAutoReverse(false);
			          	      translateTransition.play();
			        			GardenScene.fullImage.getChildren().add(bullet);
			        			//trackBullet();


			        			TranslateTransition translateTransition2 = new TranslateTransition();
				          	      //How long the animation will take
				          	      translateTransition2.setDuration(Duration.millis(3000));
				          	      translateTransition2.setNode(bulletRect);
				          	      //The displacement of the animation
				          	      translateTransition2.setByX(bulletEndPosition);
				          	      translateTransition2.setCycleCount(1000);
				          	      translateTransition2.setAutoReverse(false);
				          	      translateTransition2.play();
				          	    GardenScene.fullImage.getChildren().add(bulletRect);

		} else if (plant.getType().equals("Wallnut")) {

			game.placePlant(plant, plant.getRow(), plant.getColumn());


		} else if (plant.getType().equals("Potato Mine")) {

			plant.getPlantImage().setFitWidth(70);

			plant.getPlantImage().setPreserveRatio(true);
			game.placePlant(plant, plant.getRow(), plant.getColumn());


		} else if (plant.getType().equals("Frozen PeaShooter")) {

			game.placePlant(plant, plant.getRow(), plant.getColumn());


			//creating frozen pea bullet image
			try {
				bullet = new ImageView(new Image( new FileInputStream("PlantImages//frozen-pea-bullet.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			bulletRect = getBounds(bullet);
			bulletRect.setStroke(Color.AQUA);
			//Setting the position of the frozen pea bullet so it starts at the frozen peashooter's mouth.
			bullet.setLayoutX(xPosition + 60);
			bullet.setLayoutY(yPosition + 165);
			bulletRect = getBounds(bullet);
			bulletRect.setFill(Color.TRANSPARENT);
			bulletRect.setStroke(Color.BLACK);
		    bulletRect.setStrokeWidth(2);

			TranslateTransition translateTransition = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition.setDuration(Duration.millis(3000));
    	      translateTransition.setNode(bullet);
    	      //The displacement of the animation
    	      translateTransition.setByX(1125 - xPosition);
    	      translateTransition.setCycleCount(1000);
    	      translateTransition.setAutoReverse(false);
    	      translateTransition.play();
  			GardenScene.fullImage.getChildren().add(bullet);
  			//trackBullet();
  			TranslateTransition translateTransition2 = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition2.setDuration(Duration.millis(3000));
    	      translateTransition2.setNode(bulletRect);
    	      //The displacement of the animation
    	      translateTransition2.setByX(bulletEndPosition);
    	      translateTransition2.setCycleCount(1000);
    	      translateTransition2.setAutoReverse(false);
    	      translateTransition2.play();
    	    GardenScene.fullImage.getChildren().add(bulletRect);

		} else if (player.getPlantHeld().equals("")){
			//if there was no plant being held, it was set to blank thus the error message is set to being seen
			GardenScene.errorMessage.setStyle("-fx-font-size: 50; -fx-background-color: transparent; -fx-font-weight: bold;");
			GardenScene.errorMessage.setLayoutX(710);
			GardenScene.errorMessage.setLayoutY(3);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

			        @Override
			        public void run() {
			            Platform.runLater(new Runnable() {
			                @Override
			                public void run() {
			                	GardenScene.errorMessage.setStyle("-fx-opacity: 0.0;");
			                }
			            });
			        }
			    }, 2500);
		}

		//only if a plantImage was created, meaning there is a plant being held, that plant image will be added
		 if (plant.getPlantImage() != null) {
			//adding plant image to fullImage pane, along with the correct position
			 plant.getPlantImage().setLayoutX(xPosition + 25);
			 plant.getPlantImage().setLayoutY(yPosition + 160);
			GardenScene.fullImage.getChildren().add(plant.getPlantImage());
			//disables that garden button if there exists a plantImage, which is true only if there is a plant being held
			source.setDisable(true);
		 }

		player.setPlantHeld("");
		System.out.println(source.getText());
		//System.out.println(Arrays.deepToString(Game.theGarden));
		for(int i = 0; i<5; i++)
		{
		    for(int j = 0; j<9; j++)
		    {
		        System.out.print(Game.theGarden[i][j] + " ");
		    }
		    System.out.println();
		}
	}





	public ImageView getBullet() {
		return bullet;
	}


	public void setBullet(ImageView bullet) {
		this.bullet = bullet;
	}


	public Rectangle getBulletRect() {
		return bulletRect;
	}


	public void setBulletRect(Rectangle bulletRect) {
		this.bulletRect = bulletRect;
	}




}
