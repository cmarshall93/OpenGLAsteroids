import org.newdawn.slick.geom.Polygon;


public class AsteroidsGameBullet extends AsteroidsGameObject {

	public AsteroidsGameBullet(float x, float y) {
		super(x, y);
		model = new Polygon();
		model.addPoint(0, 5f);
		model.addPoint(5f, 5f);
		model.addPoint(5f, 0);
		model.addPoint(0,0);
		model.setLocation(position.getX(), position.getY());
		movementVector = new AsteroidsGameVector(0f, 0f);
		rotation = 0f;
	}

	@Override
	public void update() {
		if(position.getX() > OpenGLAsteroids_Program.WIN_WIDTH){
			destroyed = true;
		}
		else if(position.getX() < 0){
			destroyed = true;
		}
		else if(position.getY() > OpenGLAsteroids_Program.WIN_HEIGHT){
			destroyed = true;		}
		else if(position.getY() <  0){
			destroyed = true;
		}
		position = new AsteroidsGameVector(position.getX() + movementVector.getX(), position.getY() + movementVector.getY());
		model.setLocation(position.getX(), position.getY());
	}

}
