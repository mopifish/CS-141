// Drake Pickett
// 2.1.23
// Graphics Programming Example using "DrawingPanel.java"
/* More info on the DrawingPanel class can be found in 
	Supplement 3G at http://www.buildingjavaprograms.com/supplements4.shtml */

import java.awt.*; // Necessary for DrawingPanel class

public class GraphicsExample {
	public static void main(String[] args){
		// Initiate draw class and make a drawing panel 200x100 pixels in size
		Draw draw = new Draw(200, 100, Color.LIGHT_GRAY);

		// Draw car onto panel
		draw.car();
	}
}

// Draw class, used to store drawing functions.
class Draw {
	private static DrawingPanel panel;
	private static Graphics g;

	// Draw class constructor; Creates window and sets graphic globals
	public Draw(int window_width, int window_height, Color window_color){
		panel = new DrawingPanel(window_width, window_height);
		panel.setBackground(window_color);	

		g = panel.getGraphics();
	}

	// Draws a car
	public static void car(){
		// Body of car
		rectangle(10, 30, 100, 50, Color.BLACK);

		// Wheels of car
		oval(20, 70, 20, 20, Color.RED);
		oval(80, 70, 20, 20, Color.RED);

		// Wind shield of car	
		rectangle(80, 40, 30, 20, Color.CYAN);
	}

	// Rectangle helper function
	public static void rectangle(int x, int y, int width, int height, Color color){
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	public static void oval(int x, int y, int width, int height, Color color){
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
}