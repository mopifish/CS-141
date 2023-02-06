// Drake Pickett
// 2.2.23
// Lab 3: Cafe Wall 
// Part B: CafeWall.java
/* This program uses the provided DrawingPanel file to 
   draw the Cafe Wall illusion									*/

import java.awt.*; // Necessary for DrawingPanel class
import java.util.Random;	

public class DrakePickettCafeWall{

	// Class variables used throughout multiple methods
	private static DrawingPanel panel;
	private static Graphics g;

	public static void main(String[] args){
		createWindow(650, 400, Color.GRAY);

		// Upper left
		drawRowOfSquares(0, 0, 4, 20);

		// Mid left
		drawRowOfSquares(50, 70, 5, 30);

		// Lower left
		drawGridOfSquares(10, 150, 4, 25, 0);
		
		// Lower middle
		drawGridOfSquares(250, 200, 3, 25, 10);

		// Lower right
		drawGridOfSquares(425, 180, 5, 20, 10);

		// Upper right
		drawGridOfSquares(400, 20, 2, 35, 35);
	}

	// Creates window and sets graphic globals
	public static void createWindow(int window_width, int window_height, Color window_color){
		panel = new DrawingPanel(window_width, window_height);
		panel.setBackground(window_color);	

		g = panel.getGraphics();
	}

	// Advanced drawing functions
	public static void drawGridOfSquares(int x, int y, int numPairs, int squareSize, int rowOffset){
		// Constant, gap between each row in pixels
		int ROW_GAP = 2;

		for (int i = 0; i < numPairs*2; i++){
			// Sets x value to be offset or not depending on row
			int rowX = (i%2 != 0) ? x + rowOffset : x;
			
			drawRowOfSquares(rowX, y + (squareSize*i) + (ROW_GAP * i), numPairs, squareSize);
		}
	}
	public static void drawRowOfSquares(int x, int y, int numPairs, int squareSize){
		for (int i = 0; i < numPairs; i++){
			drawBlackSquare(x + (i*squareSize*2), y, squareSize);
			drawWhiteSquare(x + (i*squareSize*2) + squareSize, y, squareSize);
		}
	}
	public static void drawWhiteSquare(int x, int y, int size){
		drawRectangle(x, y, size, size, Color.WHITE);
	}
	public static void drawBlackSquare(int x, int y, int size){
		drawRectangle(x, y, size, size, Color.BLACK);
		g.setColor(Color.BLUE);
		g.drawLine(x, y, x+size, y+size);
		g.drawLine(x+size, y, x, y+size);
	}
	
	// Primitive drawing functions
	public static void drawRectangle(int x, int y, int width, int height, Color color){
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

}