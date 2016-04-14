package model.gameModel;

import resources.Consts;

import java.lang.IndexOutOfBoundsException;
//import controller.Hexgame;

public class Point {
	public int x;
	public int y;
	public Point() {
		x = 0;
		y = 0;
	}
	public Point(int x, int y) throws IndexOutOfBoundsException {
		if (x < 0 || y < 0 || x >= Consts.BSIZE || y >= Consts.BSIZE)
			throw new IndexOutOfBoundsException();
		this.x = x;
		this.y = y;
	}

}
