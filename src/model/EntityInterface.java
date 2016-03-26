package model;

public interface EntityInterface {
	public void resetTurn();
	public int calculateSteps(int steps);
	public void moveTo(Point p);
	public int attack();
	public void beAttacked(int damage);
}
