package model;

public interface EntityInterface {

	
	int calculateSteps(int steps);

	int attack();

	void beAttacked(int damage);

	int getTeam();

	void setTeam(int team);

	Profession getProfession();

	void setProfession(Profession prof);

	int getStrength();

	void setStrength(int strength);

	int getAgility();

	void setAgility(int agility);

	int getMaxHP();

	void setMaxHP(int hp);

	int currentHP();

	void setCurrentHP(int hp);

	int getDefense();

	void setDefense(int defense);

	String getAttackName();

	void setAttackName(String name);

	void setPos(int x, int y);

	int getXPos();

	int getYPos();

	void setMoved();

	void unsetMoved();

	boolean isMoved();
}
