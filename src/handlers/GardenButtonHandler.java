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
import handlers.SunflowerSunHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	private ImageView peaBullet;
	private ImageView frozenBullet;

	ImageView plantImage = null;
	static Rectangle bulletRect;
	static Rectangle fBulletRect;
	
	
	
	public Rectangle getBounds(ImageView z) {
		return new Rectangle( z.getLayoutX(), z.getLayoutY(), 25, 25);
	}


	public static Button errorMessage = null;

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
		Plant plant = new Plant(player.getPlantHeld());
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
			try {
				plantImage = new ImageView(new Image(new FileInputStream("PlantImages//Sunflower.gif")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

			//placing the sun gifs which is a sunButton
			sunButton = null;
			try {
				sunButton = new Sun().getSunButton();
			} catch (Exception e) {
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
			                }
			            });

			        }
			    }, 6000);

		} else if (plant.getType().equals("PeaShooter")) {
			try {
				plantImage = new ImageView(new Image(new FileInputStream("PlantImages//pea-shooter.gif")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

			//creating pea bullet image
			try {
				peaBullet = new ImageView(new Image( new FileInputStream("PlantImages//pea-bullet.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//Setting the position of the pea bullet so it starts at the peashooter's mouth.
			peaBullet.setLayoutX(xPosition + 60 );
			peaBullet.setLayoutY(yPosition + 165);

								
			bulletRect = getBounds(peaBullet);
			bulletRect.setFill(Color.TRANSPARENT);

			          	      TranslateTransition translateTransition = new TranslateTransition();
			          	      //How long the animation will take
			          	      translateTransition.setDuration(Duration.millis(3000));
			          	      translateTransition.setNode(peaBullet);
			          	      //The displacement of the animation
			          	      translateTransition.setByX(1125 - xPosition);
			          	      translateTransition.setCycleCount(1000);
			          	      translateTransition.setAutoReverse(false);
			          	      translateTransition.play();
			        			GardenScene.fullImage.getChildren().add(peaBullet);
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
			try {
				plantImage = new ImageView(new Image(new FileInputStream("PlantImages//walnut_full_life.gif")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

		} else if (plant.getType().equals("Potato Mine")) {
			try {
				plantImage = new ImageView(new Image(new FileInputStream("PlantImages//potato-mine-active.gif")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			plantImage.setFitWidth(70);
			
			plantImage.setPreserveRatio(true);
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

		} else if (plant.getType().equals("Frozen PeaShooter")) {
			try {
				plantImage = new ImageView(new Image(new FileInputStream("PlantImages//frozen-pea.gif")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			game.placePlant(plant, plant.getRow(), plant.getColumn());
			source.setDisable(true);

			//creating frozen pea bullet image
			try {
				frozenBullet = new ImageView(new Image( new FileInputStream("PlantImages//frozen-pea-bullet.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			fBulletRect = getBounds(frozenBullet);
			fBulletRect.setStroke(Color.AQUA);
			//Setting the position of the frozen pea bullet so it starts at the frozen peashooter's mouth.
			frozenBullet.setLayoutX(xPosition + 60);
			frozenBullet.setLayoutY(yPosition + 165);
			fBulletRect = getBounds(frozenBullet);
			fBulletRect.setFill(Color.TRANSPARENT);
			TranslateTransition translateTransition = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition.setDuration(Duration.millis(3000));
    	      translateTransition.setNode(frozenBullet);
    	      //The displacement of the animation
    	      translateTransition.setByX(1125 - xPosition);
    	      translateTransition.setCycleCount(1000);
    	      translateTransition.setAutoReverse(false);
    	      translateTransition.play();
  			GardenScene.fullImage.getChildren().add(frozenBullet);
  			//trackBullet();
  			TranslateTransition translateTransition2 = new TranslateTransition();
    	      //How long the animation will take
    	      translateTransition2.setDuration(Duration.millis(3000));
    	      translateTransition2.setNode(fBulletRect);
    	      //The displacement of the animation
    	      translateTransition2.setByX(bulletEndPosition);
    	      translateTransition2.setCycleCount(1000);
    	      translateTransition2.setAutoReverse(false);
    	      translateTransition2.play(); 
    	    GardenScene.fullImage.getChildren().add(fBulletRect);

		} else if (player.getPlantHeld().equals("")){
			//if there was no plant being held, it was set to blank thus an error message is created
			errorMessage = new Button("Buy a plant first!");
			errorMessage.setStyle("-fx-font-size: 50; -fx-background-color: transparent; -fx-font-weight: bold;");
			errorMessage.setLayoutX(710);
			errorMessage.setLayoutY(3);
			GardenScene.fullImage.getChildren().add(errorMessage);
		}

		//only if a plantImage was created, meaning there is a plant being held, that plant image will be added
		 if (plantImage != null) {
			//adding plant image to fullImage pane, along with the correct position
			plantImage.setLayoutX(xPosition + 25);
			plantImage.setLayoutY(yPosition + 160);
			GardenScene.fullImage.getChildren().add(plantImage);
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

	

	public static void damageZombie(Zombie zombie) {
		if (bulletRect.getBoundsInParent().intersects(zombie.newRectangle().getBoundsInParent())) {
			zombie.loseHealth(10);
		}
		else if (fBulletRect.getBoundsInParent().intersects(zombie.newRectangle().getBoundsInParent())) {
			zombie.loseHealth(10);
		}
	}

}
