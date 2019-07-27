import javafx.scene.Scene;

//Setup for the base scene/window

public abstract class BaseScene {
	private Scene scene;	//Scene where we're drawing
	private Session session;	//Access to Session
	
	public BaseScene(Session aSession) {
		this.session = aSession;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session aSession) {
		this.session = aSession;
	}
	
	
	//Setup scene (actual drawing)
	//abstract, children will setup the scene
	//example;
	//start||quit scene sets up screen with start button
	//garden scene sets up game play scene
	public abstract void setup() throws Exception;
	
	protected void display() {
		session.setScene(this.scene);
	}

}
