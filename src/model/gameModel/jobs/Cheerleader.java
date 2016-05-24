package model.gameModel.jobs;

import model.gameModel.Entity;
import model.gameModel.ObservationSubject;
import model.gameModel.skills.CheerDance;
import model.gameModel.skills.ProfessionDecorator;

public class Cheerleader extends ProfessionDecorator {
	private final static int TEAM = 0;
	private final static int MAX_HP = 75;
	private final static int STRENGTH = 30;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Cheer Dance";
	private final static String DESCRIPTION = 
			"Boosts the team's morale and stats";
	private final static int STRENGTH_AMOUNT = 10;
	private final static int DEFENSE_AMOUNT = 10;
	private final static int AGILITY_AMOUNT = 1;
	
	public Cheerleader(String name, Entity entity) {
		super(name, entity);
		super.setName(entity.getName());
		super.setTeam(TEAM);
		super.setProfessionName(this.getClass().getSimpleName());
		super.setCurrentHP(MAX_HP);
		super.setPos(entity.getXPos(), entity.getYPos());
		super.setMaxHP(MAX_HP);
		super.setStrength(STRENGTH);
		super.setAgility(AGILITY);
		super.setDefense(DEFENSE);
		super.setUpgradable(UPGRADABLE);
		super.setAttackName(ATTACK_NAME);
		super.setDescription(DESCRIPTION);
	}

	@Override
	public void invoke(Entity target, ObservationSubject subject) {
		invokeSkill(new CheerDance(STRENGTH_AMOUNT, DEFENSE_AMOUNT, 
				AGILITY_AMOUNT, subject),target);
	}
}
