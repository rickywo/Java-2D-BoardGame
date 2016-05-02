package model.gameModel.jobs;

import model.gameModel.Entity;
import model.gameModel.skills.ProfessionDecorator;

public class CombatEngineer extends ProfessionDecorator {
	private final static int TEAM = 0;
	private final static int MAX_HP = 130;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 15;
	private final static int AGILITY = 3;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Minefield";
	private final static String DESCRIPTION = "The brains of the team, "
			+ "employing sneaky tricks to sabotage enemies";
	
	public CombatEngineer(String name, Entity entity) {
		super(name, entity);
		super.setName(entity.getName());
		super.setTeam(TEAM);
		super.setProfessionName(this.getClass().getSimpleName());
		super.setCurrentHP(MAX_HP);
		super.setPos(entity.getXPos(), entity.getYPos());
		super.setMaxHP(MAX_HP);
		super.setCurrentHP(super.getCurrentHP());
		super.setStrength(STRENGTH);
		super.setAgility(AGILITY);
		super.setDefense(DEFENSE);
		super.setUpgradable(UPGRADABLE);
		super.setAttackName(ATTACK_NAME);
		super.setDescription(DESCRIPTION);
	}
}
