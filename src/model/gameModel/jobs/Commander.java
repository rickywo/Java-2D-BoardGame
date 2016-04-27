package model.gameModel.jobs;

public class Commander extends Leader {
	private final static int TEAM = 0;
	private final static int MAX_HP = 200;
	private final static int STRENGTH = 30;
	private final static int DEFENSE = 15;
	private final static int AGILITY = 3;
	private final static int ATTACK_RANGE = 0;
	private final static String ATTACK_NAME = "Charge";
	private final static String DESCRIPTION = "Leader of Human Team";
	
	public Commander(String name) {
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
