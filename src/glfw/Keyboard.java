package glfw;


import org.lwjgl.glfw.GLFW;


/** Class that wraps GLFW's methods to handle keyboard input. */
public class Keyboard {
	
	
	/** Array of all key's states - {@code true} if a key is pressed. */
	private boolean[] keyStates;
	/** Text input by the user. */
	private String textInput;
	
	
	/**
	 * Constructs new {@code Keyboard} and connects a key and char callback to the specified window.
	 * @param windowHandle Window handle
	 */
	public Keyboard( long windowHandle ) {
		keyStates = new boolean[ GLFW.GLFW_KEY_LAST - 32 ];
		textInput = "";
		
		GLFW.glfwSetKeyCallback( windowHandle,( window,key,scancode,action,mods ) -> {
			keyStates[ key - 32 ] = action != GLFW.GLFW_RELEASE;
		} );
		GLFW.glfwSetCharCallback( windowHandle,( window,codepoint ) -> {
			textInput += Character.toChars( codepoint );
		} );
	}
	
	
	/**
	 * @param key Key code
	 * @return {@code True} if specified key is pressed.
	 */
	public boolean isKeyPressed( int key ) {
		return keyStates[ key ];
	}
	
	/** @return Text input by the user. */
	public String getTextInput() {
		String s = textInput;
		textInput = "";
		return s;
	}
	
	
}
