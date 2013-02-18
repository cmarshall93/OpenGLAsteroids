import org.lwjgl.opengl.GL11;


public abstract class AsteroidsGameObject {

	protected AsteroidsGameVector position;
	protected AsteroidsGameVector movementVector;
	protected float rotation;
	protected float speed;

	protected AsteroidsGameModel model;

	public AsteroidsGameObject(float x, float y){
		position = new AsteroidsGameVector(x, y);
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
				if(speed * a < 5){
					speed *= speed * a;
				}
			}

			//if object is slowing down stop it from being able to change direction
			if(a < 1){

				movementVector = new AsteroidsGameVector(movementVector.getX() * a, 
						movementVector.getY() * a);
			}
			//otherwise calculate a direction vector and base on current rotation and use that to set the objects direction vector
			else{
			AsteroidsGameVector newMovementVector = new AsteroidsGameVector((float)(Math.sin(Math.toRadians(rotation)) * -1) * a,
					(float)(Math.cos(Math.toRadians(rotation))) * a);

			movementVector = new AsteroidsGameVector(movementVector.getX() + newMovementVector.getX(), 
					movementVector.getY() + newMovementVector.getY());
			}

		}

		System.out.println(movementVector.getX() + " : " + movementVector.getY());
	}

	public abstract void update();

	public void draw() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(position.getX(), position.getY(), 0);
			GL11.glRotatef(rotation, 0, 0, 1);

			GL11.glBegin(GL11.GL_LINE_LOOP);
			for(AsteroidsGameVector v : model.getPoints()){
				GL11.glVertex2f(0+v.getX() * 20,0 + v.getY() * 20);
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();
	}

}
