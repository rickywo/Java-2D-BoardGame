package model.gameModel;

import resources.Consts;

import java.lang.IndexOutOfBoundsException;


public class Point extends java.awt.Point{
	public int x;
	public int y;

	public Point(int x, int y) throws IndexOutOfBoundsException {
		if (x < 0 || y < 0 || x >= Consts.getBSIZE() || y >= Consts.getBSIZE())
			throw new IndexOutOfBoundsException();
		this.x = x;
		this.y = y;
	}

    public Point(java.awt.Point p) {
        if (p.x < 0 || p.y < 0 || p.x >= Consts.getBSIZE() || p.y >= Consts.getBSIZE())
            throw new IndexOutOfBoundsException();
        this.x = p.x;
        this.y = p.y;
    }

}
