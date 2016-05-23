package model.gameModel.jobs;
import model.gameModel.skills.*;
import model.gameModel.Entity;
import model.gameModel.skills.ProfessionComposition;

public class Witch extends ProfessionComposition {
	private final static int TEAM = 1;
	private final static int MAX_HP = 150;
	private final static int STRENGTH = 40;
	private final static int DEFENSE = 10;
	private final static int AGILITY = 2;
	private final static boolean UPGRADABLE = true;
	private final static String ATTACK_NAME = "Twin Spell";
	private final static String DESCRIPTION = "Restores HP to max and raises "
			+ "strength and defense";
	private final static int AMOUNT = 15;
	
	public Witch(String name, Entity entity) {
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
		invokeSkill(new TwinSpell(AMOUNT), target);
	}
}
