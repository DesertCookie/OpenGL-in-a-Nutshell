package opengl;


import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;


public class RenderManager {
	
	
	public RenderManager() {
		GL.createCapabilities();
		GL11.glClearColor( 1F,1F,1F,1F ); // white frame buffer
		GL11.glEnable( GL11.GL_BLEND ); // enable transparency
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA );
	}
	
	
	public void clearScreen() {
		GL11.glClear( GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT );
		// Color and depth information of frame buffer cleared
	}
	
	public void drawQuad( float x,float y,float w,float h,Color color ) {
		GL11.glBegin( GL11.GL_QUADS );
		
		GL11.glColor4f( color.getRed() / 255.0F,color.getGreen() / 255.0F,color.getBlue() / 255.0F,color.getAlpha() / 255.0F );
		
		GL11.glVertex2f( x,y );
		GL11.glVertex2f( x,y + h );
		GL11.glVertex2f( x + w,y + h );
		GL11.glVertex2f( x + w,y );
		
		GL11.glEnd();
	}
	
	public void drawBetterQuad( float x,float y,float w,float h,Color color ) {
		GL11.glBegin( GL11.GL_TRIANGLES );
		
		GL11.glColor4f( color.getRed() / 255.0F,color.getGreen() / 255.0F,color.getBlue() / 255.0F,color.getAlpha() / 255.0F );
		
		// Triangle 1
		GL11.glVertex2f( x,y );
		GL11.glVertex2f( x,y + h );
		GL11.glVertex2f( x + w,y + h );
		// Triangle 2
		GL11.glVertex2f( x + w,y + h );
		GL11.glVertex2f( x + w,y );
		GL11.glVertex2f( x,y );
		
		GL11.glEnd();
	}
	
	
}
