package model.gameModel.jobs;
import model.gameModel.ObservationSubject;
import model.gameModel.skills.*;
import model.gameModel.Entity;
import model.gameModel.skills.ProfessionDecorator;

public class Troll extends ProfessionDecorator {
	private final static int TEAM = 1;
	private final static int MAX_HP = 60;
	private final static int STRENGTH = 100;
	private final static int DEFENSE = 15;
	private final static int AGILITY = 1;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Ultimate Destruct";
	private final static String DESCRIPTION = 
			"Very destructive but incredibly slow";
	private final static int DAMAGE = 125;
	
	public Troll(String name, Entity entity) {
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
		invokeSkill(new UltimateDestruct(DAMAGE, subject), target);
	}
}
