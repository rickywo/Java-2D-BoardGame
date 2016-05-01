package model.gameModel.jobs;

import model.gameModel.Entity;

public class Spawn extends Entity {
	private final static int TEAM = 1;
	private final static int MAX_HP = 50;
	private final static int STRENGTH = 10;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static int ATTACK_RANGE = 1;
	private final static String ATTACK_NAME = "Fight";
	private final static String DESCRIPTION = "Base member of Alien Team";
	
	public Spawn(String name) {
		super(name);
		super.setTeam(TEAM);
		super.setProfessionName(this.getClass().getSimpleName());
		super.setMaxHP(MAX_HP);
		super.setCurrentHP(super.getMaxHP());
		super.setStrength(STRENGTH);
		super.setAgility(AGILITY);
		super.setDefense(DEFENSE);
		super.setAttackName(ATTACK_NAME);
		super.setAttackRange(ATTACK_RANGE);
		super.setDescription(DESCRIPTION);
	}

}
