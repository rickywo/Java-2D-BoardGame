package model.gameModel.jobs;

public class Defender extends Soldier {
	private final static int TEAM = 0;
	private final static int MAX_HP = 200;
	private final static int STRENGTH = 15;
	private final static int DEFENSE = 80;
	private final static int AGILITY = 1;
	private final static String ATTACK_NAME = "Great Wall";
	private final static String DESCRIPTION = "Honorable shield of the team";
	
	public Defender(String name) {
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
