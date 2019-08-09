package logic;

import java.io.FileInputStream;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Sun class creates the sun gif button with an image from the PlantImages folder
 */
public class Sun {
	
	private ImageView sunGIF = new ImageView(new Image(new FileInputStream("PlantImages//sun.gif")));
	private Button sunGif = new Button("",sunGIF);
	
	public Sun() throws Exception{
		
	}
	
	public Button getSunButton() {
		//sets the background to transparent so only the sun image is seen.
		sunGif.setStyle("-fx-background-color: transparent;");
		return sunGif;
	}
	
	public void setSunButton(Button sunGif) {
		this.sunGif = sunGif;
	}

}
