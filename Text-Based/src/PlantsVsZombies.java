import java.util.ArrayList;

public class PlantsVsZombies{
	
  
  
  private Plant[][] Garden = {{new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), 
	  new Plant(), new Plant(), new Plant()}, {new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), 
		  new Plant(), new Plant(), new Plant()}, {new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), 
			  new Plant(), new Plant(), new Plant()}, {new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), 
				  new Plant(), new Plant(), new Plant()}, {new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), new Plant(), 
					  new Plant(), new Plant(), new Plant()}};
  
  private int sunlight = 50;
  
  private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
  

  public void addZombie(Zombie z) {
	  zombies.add(z);
  }
  
  public Plant[][] getGarden() {
	  return Garden;
  }
  
  public static void printGarden(Plant mat[][]) { 
	  System.out.print("p = peashooter" + "\n" + "s = sunflower" + "\n" + "w = wallnut" + "\n" + "c = cherrybomb" + "\n" + "f = frozen peashooter" + "\n" );
	  System.out.println("Garden:");
	  System.out.println("------------------");
	// Loop through all rows 
      for (int i = 0; i < mat.length; i++) {

          // Loop through all elements of current row 
          for (int j = 0; j < mat[i].length; j++) {
              System.out.print(mat[i][j].getFirstChar() + " "); 
          }
          System.out.print("\n");
      }	
      System.out.println("------------------");
  }
  public void addSunlight(int amount) {
	  sunlight += amount;
  }
  public String toString() {
	  String str = "";
	  for (Zombie z : zombies) {
		  str += z.toString() + "\n";
	  }
	  return str;
  }
  
}
