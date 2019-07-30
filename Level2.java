import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;

public class Level2 {
	static ImageView zombies[] = new ImageView[11];
	static int counter = 0;
	
	public static void createLevel() throws FileNotFoundException {
		zombies[0] = (new Zombie("Zombie").getZombieImage());
		zombies[1] =  (new Zombie("Zombie").getZombieImage());
		zombies[2] = (new Zombie("Cone Zombie").getZombieImage());
		zombies[3] = (new Zombie("Zombie").getZombieImage());
		zombies[4] = (new Zombie("Football Zombie").getZombieImage());
		zombies[5] = (new Zombie("Flag Zombie").getZombieImage());
		zombies[6] = (new Zombie("Zombie").getZombieImage());
		zombies[7] = (new Zombie("Cone Zombie").getZombieImage());
		zombies[8] = (new Zombie("Football Zombie").getZombieImage());
		zombies[9] = (new Zombie("Zombie").getZombieImage());
		zombies[10] = (new Zombie("Football Zombie").getZombieImage());
	}
	
}
