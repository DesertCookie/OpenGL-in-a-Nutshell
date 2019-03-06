package glfw;


import org.lwjgl.glfw.GLFW;


/** Class that wraps GLFW's methods to handle keyboard input. */
public class Mouse {
	
	
	/** Array of all button's states - {@code true} if a button is pressed. */
	private boolean[] buttonStates;
	/** Position of the cursor in the window. */
	private int cursorX, cursorY;
	/** Delta of clicks the mouse wheel has been moved. */
	private int scrollDX, scrollDY;
	/** {@code True} if the cursor is inside of the window area. */
	private boolean cursorInsideWindow;
	
	
	/**
	 * Constructs new {@code Keyboard} and connects a button, cursor position, scroll wheel and cursor enter callback to the specified window.
	 * @param windowHandle Window handle
	 */
	public Mouse( long windowHandle ) {
		buttonStates = new boolean[ GLFW.GLFW_MOUSE_BUTTON_LAST ];
		scrollDX = 0;
		scrollDY = 0;
		
		GLFW.glfwSetMouseButtonCallback( windowHandle,( window,button,action,mods ) -> {
			buttonStates[ button ] = action != GLFW.GLFW_RELEASE;
		} );
		GLFW.glfwSetCursorPosCallback( windowHandle,( window,xpos,ypos ) -> {
			cursorX = (int) xpos;
			cursorY = (int) ypos;
		} );
		GLFW.glfwSetScrollCallback( windowHandle,( window,xoffset,yoffset ) -> {
			scrollDX += xoffset;
			scrollDY += yoffset;
		} );
		GLFW.glfwSetCursorEnterCallback( windowHandle,( window,entered ) -> {
			cursorInsideWindow = entered;
		} );
	}
	
	
	/**
	 * @param button Button code
	 * @return {@code True} if specified button is pressed.
	 */
	public boolean isButtonPressed( int button ) {
		return buttonStates[ button ];
	}
	
	/**
	 * @return Horizontal cursor position within the window in pixels.
	 */
	public int getCursorX() {
		if(!cursorInsideWindow)
			return -1;
		return cursorX;
	}
	
	/**
	 * @return Vertical cursor position within the window in pixels.
	 */
	public int getCursorY() {
		if(!cursorInsideWindow)
			return -1;
		return cursorY;
	}
	
	/**
	 * @return Number of clicks the scroll wheel has been moved horizontally since last call of this method.
	 */
	public int getScrollDX() {
		int dx = scrollDX;
		scrollDX = 0;
		return dx;
	}
	
	/**
	 * @return Number of clicks the scroll wheel has been moved vertically since last call of this method.
	 */
	public int getScrollDY() {
		int dy = scrollDY;
		scrollDY = 0;
		return dy;
	}
	
	/**
	 * @return {@code True} if cursor is inside the window area.
	 */
	public boolean isCursorInsideWindow() {
		return cursorInsideWindow;
	}
	
	
}
