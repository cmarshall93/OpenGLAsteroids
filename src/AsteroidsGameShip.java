import org.lwjgl.opengl.GL11;


public class AsteroidsGameShip extends AsteroidsGameObject {

	public AsteroidsGameShip(float x, float y){
		super(x, y);
		model = new AsteroidsGameModel(new AsteroidsGameVector[]{new AsteroidsGameVector(0f,1f), new AsteroidsGameVector(-1f, -1f),
		new AsteroidsGameVector(0f, 0f), new AsteroidsGameVector(1f, -1f)});

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
		else if(position.getY() < 0){
			position = new AsteroidsGameVector( position.getX(), OpenGLAsteroids_Program.WIN_HEIGHT);
		}
		position = new AsteroidsGameVector(position.getX() + movementVector.getX(), position.getY() + movementVector.getY());
	}
	
}
