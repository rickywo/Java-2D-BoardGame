package model.gameModel.jobs;

import model.gameModel.Entity;
import model.gameModel.skills.ProfessionDecorator;

public class Dragon extends ProfessionDecorator {
	private final static int TEAM = 1;
	private final static int MAX_HP = 120;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 15;
	private final static int AGILITY = 3;
	private final static String ATTACK_NAME = "Higher Power";
	private final static String DESCRIPTION = 
			"Quickly surrounds enemies with lightning quick speed";
	
	public Dragon(String name, Entity entity) {
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
		super.setAttackName(ATTACK_NAME);
		super.setDescription(DESCRIPTION);
	}
}
