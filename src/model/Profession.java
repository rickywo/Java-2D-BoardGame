package model;

public class Profession {
	
	public enum Professions {
		COMMANDER, SOLDIER, MEDIC, AREAATTACKER, WARRIOR, PROTECTOR,
		COMBATENGINEER, CHEERLEADER, 
		CHIEF, SPAWN, LADYLISA, WITCH, GOBLIN, SNIPER, TROLL, DRAGON
	}

	private Professions professionName;
	private int hp;
	private int strength;
	private int agility;
	private int defense;
	private String attackName;
	private String jobName;
	
	public Profession(Professions professionName)	
	{
		this.professionName = professionName;
		assignProfession();
	}
	
	public Profession assignProfession()
	{
		switch(professionName)
		{
			case COMMANDER:
				assignCommander();
				return this;
			case SOLDIER:
				assignSoldier();
				return this;
			case MEDIC:
				assignMedic();
				return this;
			case AREAATTACKER:
				assignAreaAttacker();
				return this;
			case WARRIOR:
				assignWarrior();
				return this;
			case PROTECTOR:
				assignProtector();
			case COMBATENGINEER:
				assignCombatEngineer();
				return this;
			case CHEERLEADER:
				assignCheerleader();
			case CHIEF:
				assignChief();
				return this;
			case SPAWN:
				assignSpawn();
			case LADYLISA:
				assignLadyLisa();
				return this;
			case WITCH:
				assignWitch();
				return this;
			case GOBLIN:
				assignGoblin();
				return this;
			case SNIPER:
				assignSniper();
			case TROLL:
				assignTroll();
				return this;
			case DRAGON:
				assignDragon();
				return this;
			default:
				return null;
		}

	}
	
	private void assignCommander(){
		this.hp = 200;
		this.strength = 30;
		this.agility = 2;
		this.defense = 15;
		this.attackName = "Charge";
		this.jobName = "Commander";
	}
	
	private void assignSoldier(){
		this.hp = 50;
		this.strength = 10;
		this.agility = 1;
		this.defense = 10;
		this.attackName = "Attack";
		this.jobName = "Soldier";
	}
	
	private void assignMedic(){
		
	}
	
	private void assignAreaAttacker(){
		
	}
	
	private void assignWarrior(){
		
	}
	
	private void assignProtector(){
		
	}
	
	private void assignCombatEngineer(){
		
	}
	
	private void assignCheerleader(){
		
	}
	
	private void assignChief(){
		
	}
	
	private void assignSpawn(){
		
	}
	
	private void assignLadyLisa(){
		
	}
	
	private void assignWitch(){
		
	}
	
	private void assignGoblin(){
		
	}
	
	private void assignSniper(){
		
	}
	
	private void assignTroll(){
		
	}
	
	private void assignDragon(){
		
	}

}
