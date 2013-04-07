import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;


public class AsteroidsGameShip extends AsteroidsGameObject {

	private boolean canFire;
	private int canFireCounter;

	public AsteroidsGameShip(float x, float y){
		super(x, y);
		model = new Polygon();
			model.addPoint(0f,20f);
			model.addPoint(-20f, -20f);
			model.addPoint(20f, -20f);
			model.setLocation(position.getX(), position.getY());
		movementVector = new AsteroidsGameVector(0f, 0f);
		rotation = 90f;
		canFire = true;
		canFireCounter = 0;
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
		model.setLocation(position.getX(), position.getY());
		
		canFireCounter++;
		if(canFireCounter > 30){
			canFireCounter = 0;
			canFire = true;
		}
	}
	
	public void fireBullet(){
		if(canFire == true){
			Shape tmpModel = model.transform(Transform.createRotateTransform((float)Math.toRadians(rotation),model.getCenterX(),model.getCenterY()));
			AsteroidsGameBullet bullet = new AsteroidsGameBullet(tmpModel.getPoints()[0], tmpModel.getPoints()[1]);
			bullet.changeMovementVector(new AsteroidsGameVector((float)(Math.sin(Math.toRadians(rotation)) * -1) * 9,
						(float)(Math.cos(Math.toRadians(rotation))) * 9));
			GameEngine.addGameObject(bullet);
			canFire = false;
		}
	}

}
