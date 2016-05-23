package model.gameModel.jobs;
import model.gameModel.skills.*;
import model.gameModel.Entity;
import model.gameModel.skills.ProfessionDecorator;

public class Warrior extends ProfessionDecorator {
	private final static int TEAM = 0;
	private final static int MAX_HP = 65;
	private final static int STRENGTH = 80;
	private final static int DEFENSE = 20;
	private final static int AGILITY = 1;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "All In";
	private final static String DESCRIPTION = 
			"Close combat specialist, slow but strong";
	private final static int DAMAGE = 100;
	
	public Warrior(String name, Entity entity) {
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
	public void invoke(Entity target) {
		invokeSkill(new AllIn(DAMAGE), target);
	}

}
