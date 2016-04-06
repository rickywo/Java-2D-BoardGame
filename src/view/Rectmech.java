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
		if(image != null)
			texture = new TexturePaint(image, rect);

		g2.setPaint(texture);
		g2.fill(rect);
		g2.setColor(Color.GRAY);
		g2.draw(rect);
	}

	/***************************************************************************
	 * Name: fill() Parameters: (i,j) : the x,y coordinates of the initial
	 * point of the rectangle n : an integer number to indicate a letter to draw
	 * in the rect g2 : the graphics context to draw on
	 *****************************************************************************/
	public static void fill(int i, int j, int n, Graphics2D g2) {
		int x = i * l;
		int y = j * l;
		if (n < 0) {
			g2.setColor(new Color(255,255,200,60));
			g2.fill(rect(x, y));
		}
	}

	// This function changes pixel location from a mouse click to a rectangle grid
	// location
	/*****************************************************************************
	 * Name: pxtoRect (pixel to Rectangle) Parameters: mx, my. 
	 *****************************************************************************/
	public static Point pxtoRect(int mx, int my) {
		Point p = new Point(-1, -1);
		mx -= BORDERS;
		my -= BORDERS;

		int x = (int) (mx / l); int y = (int) (my / l); 

		
		p.x = x;
		p.y = y;
		return p;
	}
}