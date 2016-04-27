package model.gameModel;

public interface EntityInterface {

	
	int calculateSteps(int steps);

	int attack();

	void beAttacked(int damage);

	int getTeam();

	void setTeam(int team);
	
	String getName();
	
	void setName(String name);
	
	String getProfessionName();
	
	void setProfessionName(String name);
	
	String getDescription();
	
	void setDescription(String descrip);

	int getStrength();

	void setStrength(int strength);

	int getAgility();

	void setAgility(int agility);

	int getMaxHP();

	void setMaxHP(int hp);

	int getCurrentHP();

	void setCurrentHP(int hp);

	int getDefense();

	void setDefense(int defense);

	String getAttackName();

	void setAttackName(String name);

	void setAttackRange(int range);

	int getAttackRange();

	void setPos(int x, int y);

	int getXPos();

	int getYPos();

	void setMoved();

	void unsetMoved();

	boolean isMoved();
}
