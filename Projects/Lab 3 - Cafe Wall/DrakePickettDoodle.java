// Drake Pickett
// 2.2.23
// Lab 3: Cafe Wall 
// Part A: Doodle.java
/* This program uses the provided DrawingPanel file to 
   draw a simple doodle									*/

import java.awt.*; // Necessary for DrawingPanel class
import java.util.Random;	

public class DrakePickettDoodle{

	// Class variables used throughout multiple methods

	private static DrawingPanel panel;
	private static Graphics g;

	public static void main(String[] args){
		// Initiate random class
		Random rand = new Random();

		// Create window
		createWindow(200, 200, Color.LIGHT_GRAY);

		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				int randScale = rand.nextInt(6) + 1;
				smileyFace(i * 9 * randScale, j * 9 * randScale, randScale);
			}
		}
	}

	// Creates window and sets graphic globals
	public static void createWindow(int window_width, int window_height, Color window_color){
		panel = new DrawingPanel(window_width, window_height);
		panel.setBackground(window_color);	

		g = panel.getGraphics();
	}

	// Advanced Shape Functions
	public static void smileyFace(int x, int y, int scale){
		oval(x, y, 9*scale, 9*scale, Color.ORANGE);
		oval(x+(1*scale), y+(1*scale), 7*scale, 7*scale, Color.YELLOW);
		pixel(x+(3*scale), y+(5*scale), scale, Color.BLACK);
		pixel(x+(4*scale), y+(5*scale), scale, Color.BLACK);
		pixel(x+(5*scale), y+(5*scale), scale, Color.BLACK);
		pixel(x+(2*scale), y+(4*scale), scale, Color.BLACK);
		pixel(x+(6*scale), y+(4*scale), scale, Color.BLACK);
		pixel(x+(3*scale), y+(2*scale), scale, Color.BLACK);
		pixel(x+(5*scale), y+(2*scale), scale, Color.BLACK);
	}

	// Primitive Shape Functions
	public static void pixel(int x, int y, int scale, Color color){
		rectangle(x, y, scale, scale, color);
	}
	public static void rectangle(int x, int y, int width, int height, Color color){
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	public static void oval(int x, int y, int width, int height, Color color){
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
}