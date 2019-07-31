import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.scene.image.ImageView;

	
public class Level1{
	static ImageView zombies[] = new ImageView[8];
	static int counter = 0;
	
	public static void createLevel() throws FileNotFoundException {
		zombies[0] = (new Zombie("Zombie").getZombieImage());
		zombies[1] =  (new Zombie("Zombie").getZombieImage());
		zombies[2] = (new Zombie("Cone Zombie").getZombieImage());
		zombies[3] = (new Zombie("Zombie").getZombieImage());
		zombies[4] = (new Zombie("Flag Zombie").getZombieImage());
		zombies[5] = (new Zombie("Zombie").getZombieImage());
		zombies[6] = (new Zombie("Cone Zombie").getZombieImage());
		zombies[7] = (new Zombie("Football Zombie").getZombieImage());
	}
	
	
}