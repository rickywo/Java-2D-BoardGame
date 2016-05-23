package model.gameModel.jobs;
import model.gameModel.skills.*;
import model.gameModel.Entity;
import model.gameModel.skills.ProfessionComposition;

public class LadyLisa extends ProfessionComposition {
	private final static int TEAM = 1;
	private final static int MAX_HP = 150;
	private final static int STRENGTH = 30;
	private final static int DEFENSE = 50;
	private final static int AGILITY = 2;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Eyes of Stone";
	private final static String DESCRIPTION = 
			"Drains enemy HP until 1 HP";
	private final static int REMAINDER = 1;
	
	public LadyLisa(String name, Entity entity) {
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
		invokeSkill(new EyesOfStone(REMAINDER), target);
	}
}
