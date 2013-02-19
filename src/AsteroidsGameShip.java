import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;


public class AsteroidsGameShip extends AsteroidsGameObject {

	public AsteroidsGameShip(float x, float y){
		super(x, y);
		model = new Polygon();
			model.addPoint(0f,20f);
			model.addPoint(-20f, -20f);
			model.addPoint(20f, -20f);
			//model.transform(Transform.createScaleTransform(100, 100));
			
			System.out.println(model.getCenterX() + " : " + model.getCenterY());
		movementVector = new AsteroidsGameVector(0f, 0f);
		rotation = 90f;
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
		else if(position.getY() <  0){
			position = new AsteroidsGameVector( position.getX(), OpenGLAsteroids_Program.WIN_HEIGHT);
		}
		position = new AsteroidsGameVector(position.getX() + movementVector.getX(), position.getY() + movementVector.getY());
	}

}
