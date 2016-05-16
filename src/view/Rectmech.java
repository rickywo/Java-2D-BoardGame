package view;


import resources.Consts;
import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;

/* This is the mechanism to create rectangle */

public class Rectmech {

	// Constants

	private static int l = 0; // length of each side.

	/**
	 * This functions takes the Side length in pixels and uses that as the basic
	 * dimension of the rectangle. 
	 */

	public static void setLength(int length) {
		l = length; 
	}

	/*********************************************************
	 * Name: rect() Parameters: (x,y) This point is normally the top left
	 * corner of the rectangle 
	 * Returns: a rectangle. Called from: draw(),
	 * fill() Purpose: This function takes two points that describe a rectangle.
	 *********************************************************/
	public static Rectangle rect(int x, int y) {

		x += Consts.MAP_X_OFFSET;
		y += Consts.MAP_Y_OFFSET;
		
		if (l == 0) {
			System.out.println("ERROR: size of rectangle has not been set");
			return new Rectangle();
		}
	
		return new Rectangle(x, y, l, l);
	}

	/********************************************************************
	 * Name: draw() Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle g2: the Graphics2D object to draw on. Returns: void
	 * Calls: rect() Purpose: This function draws a rect based on the initial
	 * point (x,y). 
	 *********************************************************************/
	public static void draw(int i, int j, BufferedImage image, Graphics2D g2) {
		
		int x = i * l;
		int y = j * l;
		Rectangle rect = rect(x, y);
		TexturePaint texture = null;
		// defensive design: Handle null reference

		g2.setColor(Color.LIGHT_GRAY);
		g2.draw(rect);
		g2.setColor(new Color(200,200,200,70));
		g2.fill(rect);
		if(image != null) {
			texture = new TexturePaint(image, rect);
			g2.setPaint(texture);
			g2.fill(rect);
		}
	}

	/********************************************************************
	 * Name: drawText() Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle g2: the Graphics2D object to draw on. Returns: void
	 * Calls: rect() Purpose: This function draws a rect based on the initial
	 * point (x,y).
	 *********************************************************************/
	public static void drawText(int i, int j, String s, Graphics2D g2) {

		int x = i * l;
		int y = j * l;
		Rectangle rect = rect(x, y);
		g2.setFont(new Font("TimesRoman",Font.BOLD, 12));
		g2.setColor(Color.RED);
		g2.drawString(s, (int)rect.getCenterX(), (int)rect.getMaxY());
	}

	/***************************************************************************
	 * Name: highlight() To highlight the cell at coordinates
	 * Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle g2 : the graphics context to draw on
	 *****************************************************************************/
	public static void highlight(int i, int j, Graphics2D g2) {
		int x = i * l;
		int y = j * l;
		Rectangle rect = rect(x, y);
		g2.setColor(new Color(255,255,200,60));
		g2.fill(rect);
	}

	/***************************************************************************
	 * Name: diminish() To make the cell darker
	 * Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle g2 : the graphics context to draw on
	 *****************************************************************************/
	public static void diminish(int i, int j, Graphics2D g2) {
		int x = i * l;
		int y = j * l;
		g2.setColor(new Color(100,100,100,150));
		g2.fill(rect(x, y));
	}

	// This function changes pixel location from a mouse click to a rectangle grid
	// location
	/*****************************************************************************
	 * Name: pxtoRect (pixel to Rectangle) Parameters: mx, my. 
	 *****************************************************************************/
	public static Point pxtoRect(int mx, int my) {
		Point p = new Point(-1, -1);
		mx -= Consts.MAP_X_OFFSET;
		my -= Consts.MAP_Y_OFFSET;

		int x = (int) (mx / l); int y = (int) (my / l);
		p.x = x;
		p.y = y;
		return p;
	}
}