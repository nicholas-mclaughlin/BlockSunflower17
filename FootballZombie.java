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
    private StackPane s = new StackPane();
    private Group root = new Group();
    private double ximage;
    private double yimage;

    public FootballZombie(int row, double speed, double position){
        this.row = row;
        this.zombie = new Image("zombie_football.png");
        this.displayzombie = new ImageView(zombie);
        s.setTranslateY((int)((135+(row-1)*110+55)-40+1));
        s.setTranslateX((int)(60+(column-1)*80+40)-40);
        this.yimage = (135+(row-1)*110+55)-40;
        this.ximage = (60+(column-1)*80+40)+40;     
    }

    /**
     * Method for taking away the image of the FootballZombie
     */

     public void removeimage(){
         this.s.getChildren().remove(this.displayzombie);
         this.root.getChildren().remove(this.s);
     }

     
