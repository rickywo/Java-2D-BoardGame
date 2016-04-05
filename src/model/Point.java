package model;

import java.lang.IndexOutOfBoundsException;
import controller.MainGame;

public class Point {
	private int x;
	private int y;
	public Point() {
		x = 0;
		y = 0;
	}
	public Point(int x, int y) throws IndexOutOfBoundsException {
		if (x < 0 || y < 0 || x >= MainGame.BSIZE || y >= MainGame.BSIZE) 
			throw new IndexOutOfBoundsException();
		this.x = x;
		this.y = y;
	}

}
