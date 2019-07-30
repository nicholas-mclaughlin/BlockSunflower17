import javafx.scene.Scene;

/**
 * The BaseScene class is an abstract class which
 * will be the foundation for scenes to be created
 * in the same stage/window.
 *
 */
public abstract class BaseScene {
	private Scene scene;	//Scene where we're drawing
	private Session session;	//Gives access to Session
	
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
	
	
	/**
	 * Abstract method setup() promises that every scene will have a setup() method.
	 * Child classes will setup the actual scene.
	 * example:
	 * Menu scene sets up screen with start button.
	 * 
	 * @throws Exception
	 */
	public abstract void setup() throws Exception;
	
	//display() will display the drawing/scenes
	protected void display() {
		session.setScene(this.scene);
	}

}
