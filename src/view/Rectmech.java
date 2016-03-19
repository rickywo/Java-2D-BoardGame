package view;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/* This is the mechanism to create rectangle */

public class Rectmech {

	// Constants
	
	private static BufferedImage grass;
    private static TexturePaint grasstexcture;


	private static int BORDERS = 50; // default number of pixels for the border.

	private static int l = 0; // length of each side.

	public static void setBorders(int b) {
		BORDERS = b;
	}

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

		y += BORDERS;
		x += BORDERS; 
		
		if (l == 0) {
			System.out.println("ERROR: size of hex has not been set");
			return new Rectangle();
		}
	
		return new Rectangle(x, y, l, l);
	}

	/********************************************************************
	 * Name: draw() Parameters: (i,j) : the x,y coordinates of the inital
	 * point of the rectangle g2: the Graphics2D object to draw on. Returns: void
	 * Calls: rect() Purpose: This function draws a rect based on the initial
	 * point (x,y). 
	 *********************************************************************/
	public static void draw(int i, int j, Graphics2D g2) {
		
		/* try out */
		/*try {
			File f = new File("/Users/leeshihping/Documents/workspace/HexGridBoard/bin/PNG Grass/slice16_16.png");
			if(f.exists()) {
				System.out.println("File exists");
				grass = ImageIO.read(f);
			}

        } catch (IOException ex) {
        	System.out.print(ex.toString());
        }*/
		int x = i * l;
		int y = j * l;
		Rectangle rect = rect(x, y);
		g2.setColor(Color.ORANGE);
		// g2.fillPolygon(hexmech.hex(x,y));
		//grasstexcture = new TexturePaint(grass, rect);
		//g2.setPaint(grasstexcture);
		g2.fill(rect);
		g2.setColor(Color.BLACK);
		g2.draw(rect);
	}

	/***************************************************************************
	 * Name: fill() Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle n : an integer number to indicate a letter to draw
	 * in the hex g2 : the graphics context to draw on Return: void Called from:
	 * Calls: hex() Purpose: This draws a filled in polygon based on the
	 * coordinates of the hexagon. The colour depends on whether n is negative
	 * or positive. The colour is set by hexgame.COLOURONE and
	 * hexgame.COLOURTWO. The value of n is converted to letter and drawn in the
	 * hexagon.
	 *****************************************************************************/
	public static void fill(int i, int j, int n, Graphics2D g2) {
		char c = 'o';
		int x = i * l;
		int y = j * l;
		if (n < 0) {
			g2.setColor(new Color(255,255,200));
			g2.fill(rect(x, y));
			c = (char) (-n);
			//g2.drawString("" + c, x + r + BORDERS, y + r + BORDERS + 4); // FIXME:
																			// handle
																			// XYVertex
			// g2.drawString(x+","+y, x+r+BORDERS, y+r+BORDERS+4);
		}
		if (n > 0) {
			g2.setColor(Color.BLUE);
			g2.fill(rect(x, y));
			//g2.setColor(hexgame.COLOURTWOTXT);
			c = (char) n;
			// g2.drawString("" + c, x + r + BORDERS, y + r + BORDERS + 4); // FIXME
																			// handle
																			// XYVertex
			// g2.drawString(i+","+j, x+r+BORDERS, y+r+BORDERS+4);
		}
	}

	// This function changes pixel location from a mouse click to a hex grid
	// location
	/*****************************************************************************
	 * Name: pxtoHex (pixel to hex) Parameters: mx, my. These are the
	 * co-ordinates of mouse click. Returns: point. A point containing the
	 * coordinates of the hex that is clicked in. If the point clicked is not a
	 * valid hex (ie. on the borders of the board, (-1,-1) is returned.
	 * Function: This only works for hexes in the FLAT orientation. The POINTY
	 * orientation would require a whole other function (different math). It
	 * takes into account the size of borders. It also works with XYVertex being
	 * True or False.
	 *****************************************************************************/
	public static Point pxtoRect(int mx, int my) {
		Point p = new Point(-1, -1);

		// correction for BORDERS and XYVertex
		mx -= BORDERS;
		my -= BORDERS;

		int x = (int) (mx / l); // this gives a quick value for x. It
										// works only on odd cols and doesn't
										// handle the triangle sections. It
										// assumes that the hexagon is a
										// rectangle with width s+t (=1.5*s).
		int y = (int) (my / l); // this gives the row easily. It
												// needs to be offset by h/2
												// (=r)if it is in an even
												// column

		
		p.x = x;
		p.y = y;
		return p;
	}
}