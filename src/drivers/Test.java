package drivers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Level;
import logic.Plant;
import logic.Zombie;
import logic.Level;

public class Test extends Application{
	
	private ArrayList<Shape> nodes;
	
	public void checkCollision(Plant p, Zombie z) {
		
		 ObservableBooleanValue colliding = Bindings.createBooleanBinding(new Callable<Boolean>() {

		        @Override
		        public Boolean call() throws Exception {
		            return p.getPlantRect().getBoundsInParent().intersects(z.getRect().getBoundsInParent());
		        }

		    }, p.getPlantRect().boundsInParentProperty(), z.getRect().boundsInParentProperty());

		    colliding.addListener(new ChangeListener<Boolean>() {
		        @Override
		        public void changed(ObservableValue<? extends Boolean> obs,
		                Boolean oldValue, Boolean newValue) {
		            if (newValue) {
		                System.out.println("Colliding");
		                killPlant(p);
		            } 
		           
		        }
		    });
		}
	
		
	
		public static void createZombieTransition(Zombie z) {
			TranslateTransition translateTransition2 = new TranslateTransition();
		      //How long the animation will take
		      translateTransition2.setDuration(Duration.millis(z.getSpeed()));
		      translateTransition2.setNode(z.getRect());
		      //The displacement of the animation
		      translateTransition2.setByX(250 - z.getPosition());
		      translateTransition2.setCycleCount(1);
		      translateTransition2.setAutoReverse(false);
		      translateTransition2.play(); 

		}
		
		public void killPlant(Plant p) {
			p = null;
		}
		
		
		@Override
	    public void start(Stage primaryStage) throws FileNotFoundException {
	        Group root = new Group();
	        Scene scene = new Scene(root, 500, 500);
	        nodes = new ArrayList<>();

	        //Filled rectangle
	        Rectangle rect1 = new Rectangle(10, 100, 50, 50);
	        rect1.setFill(Color.BLUE);
	        
	        Rectangle rect2 = new Rectangle(50, 100, 50, 50);
	        rect2.setFill(Color.RED);
	        nodes.add(rect1);
	        nodes.add(rect2);
	        TranslateTransition translateTransition = new TranslateTransition();
		      //How long the animation will take
		      translateTransition.setDuration(Duration.millis(5000));
		      translateTransition.setNode(rect1);
		      //The displacement of the animation
		      translateTransition.setByX(400);
		      translateTransition.setCycleCount(5);
		      translateTransition.setAutoReverse(false);
		      translateTransition.play();
		      

	        root.getChildren().addAll(nodes);
	        /*if (rect1.intersects(rect2.getBoundsInParent())) {
	        	System.out.println("Intersect");
	        } */

	        primaryStage.setTitle("java-buddy.blogspot.com");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        Level level1 = new Level(1);
	       // checkCollision(rect1, rect2);
	        
	        /*Zombie z1 = level1.getZombies()[0];
	        z1.getZombieImage().setX(200);
	        z1.getZombieImage().setY(200);
	        z1.getRect().setX(200);
	        z1.getRect().setY(200);
	        z1.getRect().setFill(Color.TRANSPARENT);
		    z1.getRect().setStroke(Color.BLACK); */
		    
	        level1.getZombies()[0].getZombieImage().setX(200);
	        level1.getZombies()[0].getZombieImage().setY(200);
	        level1.getZombies()[0].getRect().setX(200);
	        level1.getZombies()[0].getRect().setY(200);
	        level1.getZombies()[0].getRect().setFill(Color.TRANSPARENT);
	        level1.getZombies()[0].getRect().setStroke(Color.BLACK);
	        
		    createZombieTransition(level1.getZombies()[0]);
		   /* TranslateTransition translateTransition2 = new TranslateTransition();
		      //How long the animation will take
		      translateTransition2.setDuration(Duration.millis(70000));
		      translateTransition2.setNode(z1.getRect());
		      //The displacement of the animation
		      translateTransition2.setByX(-1250);
		      translateTransition2.setCycleCount(1);
		      translateTransition2.setAutoReverse(false);
		      translateTransition2.play();  */

	        root.getChildren().addAll(level1.getZombies()[0].getZombieImage(), level1.getZombies()[0].getRect());
	        
	        Plant p1 = new Plant("Wallnut");
	        
	        p1.getPlantImage().setX(100);
	        p1.getPlantImage().setY(200);
	        p1.getPlantRect().setX(100);
	        p1.getPlantRect().setY(200);
	        
	        Plant p2 = new Plant("Wallnut");
	        
	        p2.getPlantImage().setX(0);
	        p2.getPlantImage().setY(200);
	        p2.getPlantRect().setX(0);
	        p2.getPlantRect().setY(200);
	        
	        root.getChildren().addAll(p1.getPlantImage(), p1.getPlantRect(), p2.getPlantImage(), p2.getPlantRect());
	        checkCollision(p1, level1.getZombies()[0]);
	        
	        
	    }
		

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        launch(args);
	    }


}
