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
import logic.Plant;
import logic.Zombie;
import logic.Level;

public class Test extends Application{
	
	private ArrayList<Shape> nodes;
	
	public void checkCollision(Rectangle rect1, Rectangle rect2) {
		
		 ObservableBooleanValue colliding = Bindings.createBooleanBinding(new Callable<Boolean>() {

		        @Override
		        public Boolean call() throws Exception {
		            return rect1.getBoundsInParent().intersects(rect2.getBoundsInParent());
		        }

		    }, rect1.boundsInParentProperty(), rect2.boundsInParentProperty());

		    colliding.addListener(new ChangeListener<Boolean>() {
		        @Override
		        public void changed(ObservableValue<? extends Boolean> obs,
		                Boolean oldValue, Boolean newValue) {
		            if (newValue) {
		                System.out.println("Colliding");
		            } 
		            else {
		                System.out.println("Not colliding");
		            }
		        }
		    });
		}
		
		
		
		@Override
	    public void start(Stage primaryStage) throws FileNotFoundException {
	        Group root = new Group();
	        Scene scene = new Scene(root, 500, 500);
	        nodes = new ArrayList<>();

	        //Filled rectangle
	        Rectangle rect1 = new Rectangle(10, 100, 50, 50);
	        rect1.setFill(Color.BLUE);
	        
	        Rectangle rect2 = new Rectangle(200, 100, 50, 50);
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
	        if (rect1.intersects(rect2.getBoundsInParent())) {
	        	System.out.println("Intersect");
	        }

	        primaryStage.setTitle("java-buddy.blogspot.com");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	       // checkCollision(rect1, rect2);
	        
	        Zombie z1 = new Zombie("Zombie", 2);
	        z1.getZombieImage().setX(200);
	        z1.getZombieImage().setY(200);
	        z1.getRect().setX(200);
	        z1.getRect().setY(200);
	        z1.getRect().setFill(Color.TRANSPARENT);
		    z1.getRect().setStroke(Color.BLACK);
		    
		    TranslateTransition translateTransition3 = new TranslateTransition();
		    translateTransition3.setDuration(Duration.millis(5000));
		      translateTransition3.setNode(z1.getRect());
		      //The displacement of the animation
		      translateTransition3.setByX(-250);
		      translateTransition3.setCycleCount(1);
		      translateTransition3.setAutoReverse(false);
		      translateTransition3.play();

	        root.getChildren().addAll(z1.getZombieImage(), z1.getRect());
	        
	        Plant p1 = new Plant("Wallnut");
	        
	        p1.getPlantImage().setX(10);
	        p1.getPlantImage().setY(200);
	        p1.getPlantRect().setX(10);
	        p1.getPlantRect().setY(200);
	        
	        root.getChildren().addAll(p1.getPlantImage(), p1.getPlantRect());
	        checkCollision(z1.getRect(), p1.getPlantRect());
	    }
		

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        launch(args);
	    }


}
