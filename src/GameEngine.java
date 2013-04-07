import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public class GameEngine {

	private ArrayList<AsteroidsGameObject> objects;
	private static ArrayList<AsteroidsGameObject> objectsToAdd;
	private static int score; 
	private AsteroidsGameShip ship;
	private long time;
	private long timeAlive;
	private boolean stopped;

	public GameEngine(){
		objects = new ArrayList<AsteroidsGameObject>();
		objectsToAdd = new ArrayList<AsteroidsGameObject>();
	}

	public void startGame(){
		ship = new AsteroidsGameShip(OpenGLAsteroids_Program.WIN_WIDTH / 2, OpenGLAsteroids_Program.WIN_HEIGHT / 2);
		objects.add(ship);
		objects.add(new AsteroidsGameAsteroid());
		time =  System.currentTimeMillis();
		timeAlive = 0;
	}

	public void update(){
		if(!stopped){
			for(AsteroidsGameObject o : objects){
				o.update();
			}
			for(AsteroidsGameObject o : objects){
				for(AsteroidsGameObject g : objects){
					if(! o.equals(g)){
						o.checkCollisons(g);
					}
				}
			}
			ArrayList<AsteroidsGameObject> destroyed = new ArrayList<AsteroidsGameObject>();
			for(AsteroidsGameObject o : objects){
				if(o.isDestroyed()){
					destroyed.add(o);
				}
			}

			objects.removeAll(destroyed);

			if(objectsToAdd.size() != 0){
				for(AsteroidsGameObject o: objectsToAdd){
					objects.add(o);
				}
				objectsToAdd.clear();
			}

			if(time + 2000 < System.currentTimeMillis()){
				objects.add(new AsteroidsGameAsteroid());
				time = System.currentTimeMillis();
			}

			timeAlive++;

			if(ship.isDestroyed()){
				stopped = true;
			}
		}
	}

	public void checkInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) && !ship.isDestroyed()){
			ship.setMovementVector(1.2f);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !ship.isDestroyed()){
			ship.setMovementVector(0.8f);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !ship.isDestroyed()){
			ship.rotate(10);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !ship.isDestroyed()){
			ship.rotate(-10);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !ship.isDestroyed()){
			ship.fireBullet();
		}
	}

	public void renderGame(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		for(AsteroidsGameObject o: objects){
			o.draw();
		}
		Integer i = (int)timeAlive/60;
		String out = "Time spent alive : " + i.toString() + "    Score : " + score;
		if(stopped){
			out+="\t\t\t\t\tGAME OVER";
		}
		Display.setTitle(out);
	}

	public static void addGameObject(AsteroidsGameObject object){
		if(objectsToAdd != null){
			objectsToAdd.add(object);
		}
	}

	public static void incrementScore(){
		score++;
	}
}
