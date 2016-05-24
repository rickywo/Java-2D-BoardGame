package model.gameModel.jobs;
import model.gameModel.ObservationSubject;
import model.gameModel.skills.*;
import model.gameModel.Entity;
import model.gameModel.skills.ProfessionDecorator;

public class Medic extends ProfessionDecorator {
	private final static int TEAM = 0;
	private final static int MAX_HP = 150;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Heal";
	private final static String DESCRIPTION = 
			"White magic healer";
	private final static int AMOUNT = 50;
	
	public Medic(String name, Entity entity) {
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
		invokeSkill(new Heal(50, subject), target);
	}

}
