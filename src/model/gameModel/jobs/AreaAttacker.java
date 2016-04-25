package model.gameModel.jobs;

public class AreaAttacker extends Soldier {
	private final static int TEAM = 0;
	private final static int MAX_HP = 80;
	private final static int STRENGTH =50;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static String ATTACK_NAME = "Area Blast";
	private final static String DESCRIPTION = 
			"Attacks enemies within a wide radius";
	
	public AreaAttacker(String name) {
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
