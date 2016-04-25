package model.gameModel.jobs;

public class LadyLisa extends Spawn {
	private final static int TEAM = 1;
	private final static int MAX_HP = 150;
	private final static int STRENGTH = 30;
	private final static int DEFENSE = 50;
	private final static int AGILITY = 2;
	private final static String ATTACK_NAME = "Eyes of Stone";
	private final static String DESCRIPTION = 
			"A Madusa with special ability to make enemies retreat";
	
	public LadyLisa(String name) {
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
