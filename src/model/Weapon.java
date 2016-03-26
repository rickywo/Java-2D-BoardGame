package model;

//import model.Profession.Professions;

public class Weapon {

	public enum Weapons {
		CANNON, GUN, MAGICALHANDS, SHIELD, COMBATKIT, FLAG;
	}
	
	//stores 1 human job, 1 alien job
	//private String[] professions = new String[2];	
	private Weapons weaponName;
	private String name;
	
	public Weapon(Weapons weaponName) {
		this.weaponName = weaponName;
		name = "";
		createWeapon();
	}
	
	private void createWeapon(){
		switch(weaponName)
		{
			case CANNON:
				createCannon();
				break;
			case GUN:
				createGun();
				break;
			case MAGICALHANDS:
				createMagicalHands();
				break;
			case SHIELD:
				createShield();
				break;
			case COMBATKIT:
				createCombatKit();
				break;
			case FLAG:
				createFlag();
				break;
		}
	}
	
	private void createCannon(){	//area attacker, sniper
		name = "Cannon";
	}
	
	private void createGun(){	//warrior, goblin
		name = "Gun";
	}
	
	private void createMagicalHands(){	//medic, witch
		name = "Magical Hands";
	}
	
	private void createShield(){	//protector, ladylisa
		name = "Shield";
	}
	
	private void createCombatKit(){	//combat engineer, dragon
		name = "Combat Kit";
	}
	
	private void createFlag(){	//cheerleader, troll
		name = "Flag";
	}
	
	public Weapon getWeapon(){
		return this;
	}
	
	public String getName(){
		return name;
	}

}
