import java.util.Random;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;


public class AsteroidsGameAsteroid extends AsteroidsGameObject {

	private Random random;
	private float edgeSize;

	public AsteroidsGameAsteroid(){
		super(0 , 0);
		random = new Random();
		edgeSize = random.nextInt(20) + 1;
		model = new Polygon();
				model.addPoint((edgeSize * -1) - random.nextFloat(),edgeSize + random.nextFloat());
				model.addPoint((edgeSize * -1) - random.nextFloat(), 0 + random.nextFloat());
				model.addPoint((edgeSize * -1) - random.nextFloat(), (edgeSize * -1) - random.nextFloat());
				model.addPoint(0 + random.nextFloat(),(edgeSize * -1) - random.nextFloat());
				model.addPoint(edgeSize + random.nextFloat(),(edgeSize * -1) - random.nextFloat());
				model.addPoint(edgeSize + random.nextFloat(), 0 + random.nextFloat());
				model.addPoint(edgeSize + random.nextFloat(), edgeSize + random.nextFloat());
				model.addPoint(0f + random.nextFloat(), edgeSize + random.nextFloat());
		rotation = random.nextFloat() * 360;
		setMovementVector(random.nextFloat() * 3);
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
	}

}
