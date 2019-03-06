package glfw;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;


/** Class that wraps GLFW's methods to create and handle a window. */
public class Window {
	
	
	/** {@code True} if GLFW already has been initialized. */
	private static boolean glfwInitialized;
	
	
	/** Window handle. */
	private long windowHandle;
	
	
	/**
	 * Constructs new {@code Window} and initializes GLFW if GLFW is not yet initialized.
	 * @param title  Title as {@code String}
	 * @param width  Horizontal size in pixels
	 * @param height Vertical size in pixels
	 */
	public Window( String title,int width,int height ) {
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
		
		windowHandle = GLFW.glfwCreateWindow( width,height,title,MemoryUtil.NULL,MemoryUtil.NULL );
		if(windowHandle == MemoryUtil.NULL) throw new IllegalStateException( "GLFW window creation failed" );
		// Window successfully created
		
		GLFW.glfwMakeContextCurrent( windowHandle );
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
		GLFW.glfwSetWindowTitle( windowHandle,title );
	}
	
	/**
	 * Changes the window's width and height.
	 * @param width  New Horizontal size in pixels
	 * @param height New vertical size in pixels
	 */
	public void setSize( int width,int height ) {
		GLFW.glfwSetWindowSize( windowHandle,width,height );
	}
	
	/**
	 * Changes the window's position.
	 * @param xPos New horizontal position in pixels
	 * @param yPos New vertical position in pixels
	 */
	public void setPos( int xPos,int yPos ) {
		GLFW.glfwSetWindowPos( windowHandle,xPos,yPos );
	}
	
	/** @return Horizontal size of the window in pixels. */
	public int getWidth() {
		int[] widthArray = new int[ 1 ];
		GLFW.glfwGetWindowSize( windowHandle,widthArray,null );
		return widthArray[ 0 ];
	}
	
	/** @return Vertical size of the window in pixels. */
	public int getHeight() {
		int[] heightArray = new int[ 1 ];
		GLFW.glfwGetWindowSize( windowHandle,null,heightArray );
		return heightArray[ 0 ];
	}
	
	/** @return Horizontal position of the window in pixels. */
	public int getXPos() {
		int[] xPosArray = new int[ 1 ];
		GLFW.glfwGetWindowPos( windowHandle,xPosArray,null );
		return xPosArray[ 0 ];
	}
	
	/** @return Vertical position of the window in pixels. */
	public int getYPos() {
		int[] yPosArray = new int[ 1 ];
		GLFW.glfwGetWindowPos( windowHandle,null,yPosArray );
		return yPosArray[ 0 ];
	}
	
	/** @return {@code False} while the user hasn't attempted to close the window. */
	public boolean isOpen() {
		return !GLFW.glfwWindowShouldClose( windowHandle );
	}
	
	/** Makes the window visible. */
	public void show() {
		GLFW.glfwShowWindow( windowHandle );
	}
	
	/** Makes the window invisible. */
	public void hide() {
		GLFW.glfwHideWindow( windowHandle );
	}
	
	/** Swaps the window's rendering buffers. */
	public void swapBuffers() {
		GLFW.glfwSwapBuffers( windowHandle );
	}
	
	/** Destroys native resources allocated by this window. */
	public void destroy() {
		GLFW.glfwDestroyWindow( windowHandle );
	}
	
	
}
