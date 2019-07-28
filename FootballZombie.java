import javafx.scene.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;

public class FootballZombie implements Zombie{
    private int row;
    private double speed;
    private double position;
    private Image zombie;
    private Image displayzombie;

    public FootballZombie(int row, double speed, double position){
        this.row = row;
        this.zombie = new Image("zombie_football.png");
        this.displayzombie = new ImageView(zombie);
        
    }
}