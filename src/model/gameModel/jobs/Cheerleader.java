package model.gameModel.jobs;

public class Cheerleader extends Soldier {
	private final static int TEAM = 0;
	private final static int MAX_HP = 75;
	private final static int STRENGTH = 30;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static String ATTACK_NAME = "Cheer Dance";
	private final static String DESCRIPTION = 
			"Boosts the team's morale and stats";
	
	public Cheerleader(String name) {
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
