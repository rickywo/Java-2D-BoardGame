package model.gameModel.jobs;

public class Troll extends Spawn {
	private final static int TEAM = 1;
	private final static int MAX_HP = 60;
	private final static int STRENGTH = 100;
	private final static int DEFENSE = 15;
	private final static int AGILITY = 1;
	private final static String ATTACK_NAME = "Ultimate Destruct";
	private final static String DESCRIPTION = 
			"Very destructive but incredibly slow";
	
	public Troll(String name) {
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
