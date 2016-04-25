package model.gameModel.jobs;

public class Sniper extends Spawn{
	private final static int TEAM = 1;
	private final static int MAX_HP = 90;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 1;
	private final static String ATTACK_NAME = "Long Laser";
	private final static String DESCRIPTION = "Specialises in pin pointed "
			+ "long range attacks";
	
	public Sniper(String name) {
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
