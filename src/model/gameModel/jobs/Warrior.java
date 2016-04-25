package model.gameModel.jobs;

public class Warrior extends Soldier {
	private final static int TEAM = 0;
	private final static int MAX_HP = 65;
	private final static int STRENGTH = 80;
	private final static int DEFENSE = 20;
	private final static int AGILITY = 1;
	private final static String ATTACK_NAME = "All In";
	private final static String DESCRIPTION = 
			"Close combat specialist, slow but strong";
	
	public Warrior(String name) {
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
