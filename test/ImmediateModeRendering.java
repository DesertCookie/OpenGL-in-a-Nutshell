import glfw.Window;
import opengl.RenderManager;
import org.lwjgl.glfw.GLFW;

import java.awt.*;


public class ImmediateModeRendering {
	
	
	public static void main( String[] args ) {
		Window window = new Window( "My Window",1280,720 );
		RenderManager renderManager = new RenderManager();
		
		window.show();
		while( window.isOpen() ) {
			GLFW.glfwPollEvents();
			renderManager.clearScreen();
			
			renderManager.drawBetterQuad( -0.6F,-0.4F,1.0F,1.0F,Color.GREEN ); // green rectangle
			renderManager.drawBetterQuad( -0.5F,-0.5F,1.0F,1.0F,new Color( 255,0,0,200 ) ); // red rectangle
			renderManager.drawBetterQuad( 0.0F,0.0F,0.25F,0.35F,new Color( 0,0,200,255 ) ); // blue rectangle
			renderManager.drawPixelPerfectQuad( window,50,50,615,335,new Color( 0,0,0,25 ) );
			
			window.swapBuffers();
		}
		window.hide();
		
		window.destroy();
	}
	
	
}
