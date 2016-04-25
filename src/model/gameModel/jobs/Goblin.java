package model.gameModel.jobs;

public class Goblin extends Spawn {
	private final static int TEAM = 1;
	private final static int MAX_HP = 70;
	private final static int STRENGTH = 35;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static String ATTACK_NAME = "Double Shot";
	private final static String DESCRIPTION = 
			"Attacks closest two enemies at once";
	
	public Goblin(String name) {
		super(name);
		super.setTeam(TEAM);
		super.setProfessionName(this.getClass().getSimpleName());
		super.setMaxHP(MAX_HP);
		super.setCurrentHP(super.getCurrentHP());
		super.setStrength(STRENGTH);
		super.setAgility(AGILITY);
		super.setDefense(DEFENSE);
		super.setAttackName(ATTACK_NAME);
		super.setDescription(DESCRIPTION);
	}
}
