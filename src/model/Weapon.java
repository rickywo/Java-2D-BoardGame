package model;

import model.Profession.Professions;

public class Weapon {

	public enum Weapons {
		CANNON, GUN, MAGICALHANDS, SHIELD, COMBATKIT, FLAG;
	}
	
	//stores 1 human job, 1 alien job
	private Profession[] professions = new Profession[2];	
	private Weapons weaponName;
	
	public Weapon(Weapons weaponName) {
		this.weaponName = weaponName;
		linkProfessions();
	}
	
	private void linkProfessions(){
		switch(weaponName)
		{
			case CANNON:
				professions[0] = new Profession(Professions.AREAATTACKER);
				professions[1] = new Profession(Professions.SNIPER);
				break;
			case GUN:
				professions[0] = new Profession(Professions.WARRIOR);
				professions[1] = new Profession(Professions.GOBLIN);
				break;
			case MAGICALHANDS:
				professions[0] = new Profession(Professions.MEDIC);
				professions[1] = new Profession(Professions.WITCH);
				break;
			case SHIELD:
				professions[0] = new Profession(Professions.PROTECTOR);
				professions[1] = new Profession(Professions.LADYLISA);
				break;
			case COMBATKIT:
				professions[0] = new Profession(Professions.COMBATENGINEER);
				professions[1] = new Profession(Professions.DRAGON);
				break;
			case FLAG:
				professions[0] = new Profession(Professions.CHEERLEADER);
				professions[1] = new Profession(Professions.TROLL);
				break;
		}
	}

}
