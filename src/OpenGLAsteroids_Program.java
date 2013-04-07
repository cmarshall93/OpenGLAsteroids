import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class OpenGLAsteroids_Program {

	public static final int WIN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100;
	public static final int WIN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100;

	private GameEngine eng;

	private int frameCounter;
	private boolean isCounting;

	public OpenGLAsteroids_Program(){
		eng = new GameEngine();
		frameCounter = 0;
		try {
			Display.setDisplayMode(new DisplayMode(WIN_WIDTH,WIN_HEIGHT));
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		OpenGLAsteroids_Program  prog = new OpenGLAsteroids_Program();
		prog.run();
	}

	private void run(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0f, WIN_WIDTH, WIN_HEIGHT, 0.0f, 0.0f, 1.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		eng.startGame();
		while(!Display.isCloseRequested()){
			if(isCounting == false){
				eng.checkInput();
				isCounting = true;
			}

			eng.update();
			eng.renderGame();

			Display.update();
			Display.sync(60);
			if(isCounting == true){
				frameCounter++;
				if(frameCounter == 5){
					frameCounter = 0;
					isCounting = false;		
				}
			}
		}
		Display.destroy();
	}

}
