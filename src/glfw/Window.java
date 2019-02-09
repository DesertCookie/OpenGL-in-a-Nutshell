package glfw;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;


/** Class that wraps GLFW's methods to create and handle a window. */
public class Window {
	
	
	/** {@code True} if GLFW already has been initialized. */
	private static boolean glfwInitialized;
	
	
	/** Window handle. */
	private long glfwWindow;
	
	
	/** Constructs new {@code Window} and initializes GLFW if GLFW is not yet initialized. */
	public Window() {
		if(!glfwInitialized) {
			GLFWErrorCallback.createPrint( System.err ).set();
			
			boolean success;
			success = GLFW.glfwInit();
			if(!success) throw new IllegalStateException( "GLFW initialization failed" );
			
			glfwInitialized = true;
		}
		// GLFW successfully initialized
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint( GLFW.GLFW_RESIZABLE,GLFW.GLFW_FALSE ); // user cannot resize window
		GLFW.glfwWindowHint( GLFW.GLFW_VISIBLE,GLFW.GLFW_FALSE ); // window is not automatically visible after creation
		GLFW.glfwWindowHint( GLFW.GLFW_CONTEXT_VERSION_MAJOR,3 );
		GLFW.glfwWindowHint( GLFW.GLFW_CONTEXT_VERSION_MINOR,3 );
		
		glfwWindow = GLFW.glfwCreateWindow( 1280,720,"Window Title",MemoryUtil.NULL,MemoryUtil.NULL );
		if(glfwWindow == MemoryUtil.NULL) throw new IllegalStateException( "GLFW window creation failed" );
		// Window successfully created
		
		GLFW.glfwMakeContextCurrent( glfwWindow );
		// OpenGL context successfully created
	}
	
	
	/** Requests updates from the operating system. */
	public static void pollEvents() {
		GLFW.glfwPollEvents();
	}
	
	/** Destroys native resources allocated by GLFW. */
	public static void terminateGlfw() {
		GLFW.glfwTerminate();
	}
	
	
	/**
	 * Changes the window's title.
	 * @param title New title as {@code String}
	 */
	public void setTitle( String title ) {
		GLFW.glfwSetWindowTitle( glfwWindow,title );
	}
	
	/**
	 * Changes the window's width and height.
	 * @param width  New Horizontal size in pixels
	 * @param height New vertical size in pixels
	 */
	public void setSize( int width,int height ) {
		GLFW.glfwSetWindowSize( glfwWindow,width,height );
	}
	
	/**
	 * Changes the window's position.
	 * @param xPos New horizontal position in pixels
	 * @param yPos New vertical position in pixels
	 */
	public void setPos( int xPos,int yPos ) {
		GLFW.glfwSetWindowPos( glfwWindow,xPos,yPos );
	}
	
	/** @return Horizontal size of the window in pixels. */
	public int getWidth() {
		int[] widthArray = new int[ 1 ];
		GLFW.glfwGetWindowSize( glfwWindow,widthArray,null );
		return widthArray[ 0 ];
	}
	
	/** @return Vertical size of the window in pixels. */
	public int getHeight() {
		int[] heightArray = new int[ 1 ];
		GLFW.glfwGetWindowSize( glfwWindow,null,heightArray );
		return heightArray[ 0 ];
	}
	
	/** @return Horizontal position of the window in pixels. */
	public int getXPos() {
		int[] xPosArray = new int[ 1 ];
		GLFW.glfwGetWindowPos( glfwWindow,xPosArray,null );
		return xPosArray[ 0 ];
	}
	
	/** @return Vertical position of the window in pixels. */
	public int getYPos() {
		int[] yPosArray = new int[ 1 ];
		GLFW.glfwGetWindowPos( glfwWindow,null,yPosArray );
		return yPosArray[ 0 ];
	}
	
	/** Makes the window visible. */
	public void show() {
		GLFW.glfwShowWindow( glfwWindow );
	}
	
	/** Makes the window invisible. */
	public void hide() {
		GLFW.glfwHideWindow( glfwWindow );
	}
	
	/** Swaps the window's rendering buffers. */
	public void swapBuffers() {
		GLFW.glfwSwapBuffers( glfwWindow );
	}
	
	/** Destroys native resources allocated by this window. */
	public void destroy() {
		GLFW.glfwDestroyWindow( glfwWindow );
	}
	
	
}
