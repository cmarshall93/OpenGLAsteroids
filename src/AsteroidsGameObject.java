import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Transform;


public abstract class AsteroidsGameObject {

	protected AsteroidsGameVector position;
	protected AsteroidsGameVector movementVector;
	protected float rotation;
	protected float speed;
	protected boolean destroyed;

	protected Polygon model;

	public AsteroidsGameObject(float x, float y){
		position = new AsteroidsGameVector(x, y);
		destroyed = false;
	}

	public boolean isDestroyed(){
		return destroyed;
	}

	public void rotate(float n){
		rotation += n;
		if(rotation > 360){
			rotation = 0;
		}
		if(rotation < 0){
			rotation = 360;
		}
	}

	public void setMovementVector(float a){
		//check is object is not moving, if so get it moving
		if(speed == 0){
			speed = a;
			movementVector = new AsteroidsGameVector((float)(Math.sin(Math.toRadians(rotation)) * -1) * speed,
					(float)(Math.cos(Math.toRadians(rotation))) * speed);
		}
		else{
			//if accelration is less than one(slowing down)
			if(a < 1){
				if(speed * a > 0){
					speed = speed * a;
				}
			}
			//if acceleration is more than 1(speeding up)
			else{
				if(speed * a < 10){
					speed = speed * a;
				}
			}

			//if object is slowing down stop it from being able to change direction
			if(a < 1){

				movementVector = new AsteroidsGameVector(movementVector.getX() * a, 
						movementVector.getY() * a);
			}
			//otherwise calculate a direction vector and base on current rotation and use that to set the objects direction vector
			else if (speed * a < 10){
				AsteroidsGameVector newMovementVector = new AsteroidsGameVector((float)(Math.sin(Math.toRadians(rotation)) * -1) * a,
						(float)(Math.cos(Math.toRadians(rotation))) * a);

				movementVector = new AsteroidsGameVector(movementVector.getX() + newMovementVector.getX(), 
						movementVector.getY() + newMovementVector.getY());
			}

		}
	}
	public void collide(){
		destroyed = true;
	}
	public void checkCollisons(AsteroidsGameObject o){
		if(model.intersects(o.getModel())){
			//o.collide();
			collide();
			System.out.println(o.toString() + " :: " + toString());
		}
	}
	
	public void changeMovementVector(AsteroidsGameVector vector){
		movementVector = vector;
	}
	
	public void changePosition(AsteroidsGameVector vector){
		position = vector;
		model.setLocation(position.getX(), position.getY());
	}

	private Polygon getModel() {
		return model;
	}
	
	private AsteroidsGameVector getPosition() {
		return position;
	}

	public abstract void update();

	public void draw() {
		GL11.glPushMatrix();
		{
			GL11.glColor3f(1f,1f,1f);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			Shape tmpModel = model.transform(Transform.createRotateTransform((float)Math.toRadians(rotation),model.getCenterX(),model.getCenterY()));
			for(int i = 0; i + 1 < model.getPoints().length; i+= 2){
				GL11.glVertex2f(tmpModel.getPoints()[i], tmpModel.getPoints()[i + 1]);
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();

	}

}
