package model.gameModel.jobs;

public class Witch extends Spawn{
	private final static int TEAM = 1;
	private final static int MAX_HP = 150;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static String ATTACK_NAME = "Twin Spell";
	private final static String DESCRIPTION = "Simulateously heals team and "
			+ "weakens enemy's defense";
	
	public Witch(String name) {
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
