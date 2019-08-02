import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;

public class Level2 {
	static Zombie zombies[] = new Zombie[11];
	static int counter = 0;
	
	public static void createLevel() throws FileNotFoundException {
		zombies[0] = (new Zombie("Zombie"));
		zombies[1] =  (new Zombie("Zombie"));
		zombies[2] = (new Zombie("Cone Zombie"));
		zombies[3] = (new Zombie("Zombie"));
		zombies[4] = (new Zombie("Football Zombie"));
		zombies[5] = (new Zombie("Flag Zombie"));
		zombies[6] = (new Zombie("Zombie"));
		zombies[7] = (new Zombie("Cone Zombie"));
		zombies[8] = (new Zombie("Football Zombie"));
		zombies[9] = (new Zombie("Zombie"));
		zombies[10] = (new Zombie("Football Zombie"));
	}
	
}
