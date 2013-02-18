import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class GameEngine {

	private ArrayList<AsteroidsGameObject> objects;
	private AsteroidsGameShip ship;
	
	public GameEngine(){
		objects = new ArrayList<AsteroidsGameObject>();
		ship = new AsteroidsGameShip(OpenGLAsteroids_Program.WIN_WIDTH / 2, OpenGLAsteroids_Program.WIN_HEIGHT / 2);
		objects.add(ship);
		objects.add(new AsteroidsGameAsteroid());
		objects.add(new AsteroidsGameAsteroid());
	}
	
	public void startGame(){
		
	}
	
	public void update(){
		for(AsteroidsGameObject o : objects){
			o.update();
		}
	}
	
	public void checkInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			ship.setMovementVector(1.2f);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			ship.setMovementVector(0.8f);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			ship.rotate(10);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			ship.rotate(-10);
		}
	}
	
	public void renderGame(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		for(AsteroidsGameObject o: objects){
			o.draw();
		}
	}
	
}
