import java.util.Random;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;


public class AsteroidsGameAsteroid extends AsteroidsGameObject {

	private Random random;
	private float edgeSize;

	public AsteroidsGameAsteroid(){
		super(-30 , -30);
		random = new Random();
		edgeSize = random.nextInt(20) + 1;
		setUpModel(edgeSize);
		rotation = random.nextFloat() * 360;
		setMovementVector(random.nextFloat() * 3);
	}
	
	public AsteroidsGameAsteroid(float edgeSize,float x, float y){
		super(x,y);
		random = new Random();
		this.edgeSize = edgeSize;
		setUpModel(edgeSize);
		rotation = random.nextFloat() * 360;
		setMovementVector(random.nextFloat() * 3);
	}

	public void collide(){
		destroyed = true;
		if(edgeSize > 5){
			float tmpEdgeSize = edgeSize / 2;
			while(tmpEdgeSize > 1){
			//if(tmpEdgeSize > 2){
				AsteroidsGameAsteroid newAsteroid = new AsteroidsGameAsteroid(tmpEdgeSize,position.getX(),position.getY());
				newAsteroid.changeMovementVector(new AsteroidsGameVector(movementVector.getX(), movementVector.getY()));
				newAsteroid.changePosition(new AsteroidsGameVector(position.getX() + model.getBoundingCircleRadius() * (random.nextInt(3) - 1) + edgeSize,
						position.getY() + model.getBoundingCircleRadius() * (random.nextInt(3) - 1) + edgeSize));
				GameEngine.addGameObject(newAsteroid);
				tmpEdgeSize = tmpEdgeSize / ((random.nextInt(3) + 2) - random.nextFloat());
			}
		}
	}
	
	@Override
	public void update() {
		if(position.getX() > OpenGLAsteroids_Program.WIN_WIDTH){
			position = new AsteroidsGameVector(0, position.getY());
		}
		else if(position.getX() < 0){
			position = new AsteroidsGameVector(OpenGLAsteroids_Program.WIN_WIDTH, position.getY());
		}
		else if(position.getY() > OpenGLAsteroids_Program.WIN_HEIGHT){
			position = new AsteroidsGameVector( position.getX(),0);
		}
		else if(position.getY() < 0){
			position = new AsteroidsGameVector( position.getX(), OpenGLAsteroids_Program.WIN_HEIGHT);
		}
		position = new AsteroidsGameVector(position.getX() + movementVector.getX(), position.getY() + movementVector.getY());
		model.setLocation(position.getX(), position.getY());
	}
	
	private void setUpModel(float edgeSize){
		model = new Polygon();
		model.addPoint((edgeSize * -1) - (random.nextFloat() * 10),edgeSize + (random.nextFloat() * 10));
		model.addPoint((edgeSize * -1) - (random.nextFloat() * 10), 0 + (random.nextFloat() * 10));
		model.addPoint((edgeSize * -1) - (random.nextFloat() * 10), (edgeSize * -1) - (random.nextFloat() * 10));
		model.addPoint(0 + (random.nextFloat() * 10),(edgeSize * -1) - (random.nextFloat() * 10));
		model.addPoint(edgeSize + (random.nextFloat() * 10),(edgeSize * -1) - (random.nextFloat() * 10));
		model.addPoint(edgeSize + (random.nextFloat() * 10), 0 + (random.nextFloat() * 10));
		model.addPoint(edgeSize + (random.nextFloat() * 10), edgeSize + (random.nextFloat() * 10));
		model.addPoint(0f + (random.nextFloat() * 10), edgeSize + (random.nextFloat() * 10));
		model.setLocation(position.getX(), position.getY());
	}

}
