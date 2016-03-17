package model;

public interface EntityInterface {
	public void resetTrun();
	public int calSteps(int steps);
	public void moveTo(Point p);
	public int attack();
	public void beAttacked(int damage);
}
