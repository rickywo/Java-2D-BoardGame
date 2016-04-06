package model;

public class Profession {

	private Professions professionName;
	private Weapon weapon;
	private String name;
	private String description;
	//private int id; //0-19 as id for each job. 0-9 humans, 10-19 aliens
//	private int currentHP;
//	private int maxHP;
//	private int strength;
//	private int agility;
//	private int defense;
//	private String attackName;

	
	public Profession(Professions professionName, Entity entity)	
	{
		this.professionName = professionName;
		this.name = "";
		this.weapon = null;
		this.description = "";
		assignProfession(entity);
	}
	
//	public Profession (Professions professionName)
//	{
//		this.professionName = professionName;
//		assignProfession();
//	}
	
	public Profession assignProfession(Entity entity)
	{
		switch(professionName)
		{
			case COMMANDER:
				assignCommander(entity);
				return this;
			case SOLDIER:
				assignSoldier(entity);
				return this;
			case MEDIC:
				assignMedic(entity);
				return this;
			case AREAATTACKER:
				assignAreaAttacker(entity);
				return this;
			case WARRIOR:
				assignWarrior(entity);
				return this;
			case PROTECTOR:
				assignProtector(entity);
			case COMBATENGINEER:
				assignCombatEngineer(entity);
				return this;
			case CHEERLEADER:
				assignCheerleader(entity);
			case CHIEF:
				assignChief(entity);
				return this;
			case SPAWN:
				assignSpawn(entity);
			case LADYLISA:
				assignLadyLisa(entity);
				return this;
			case WITCH:
				assignWitch(entity);
				return this;
			case GOBLIN:
				assignGoblin(entity);
				return this;
			case SNIPER:
				assignSniper(entity);
			case TROLL:
				assignTroll(entity);
				return this;
			case DRAGON:
				assignDragon(entity);
				return this;
			default:
				return null;
		}

	}
	
	private void assignCommander(Entity entity){
		int hp = 200;
		entity.setMaxHP(hp);
		entity.setCurrentHP(hp);
		entity.setStrength(30);
		entity.setAgility(2);
		entity.setDefense(15);
		entity.setAttackName("Charge");
		this.name = Professions.COMMANDER.getCharacterName();
		this.description = "Leader of Human Team";
	}
	
	private void assignSoldier(Entity entity){
		int hp = 50;
		entity.setMaxHP(hp);
		entity.setCurrentHP(hp);
		entity.setStrength(10);
		entity.setAgility(1);
		entity.setDefense(10);
		entity.setAttackName("Attack");
		this.name = Professions.SOLDIER.getCharacterName();
		this.description = "Base member of Human Team";
	}
	
	private void assignMedic(Entity entity){
		
	}
	
	private void assignAreaAttacker(Entity entity){
		
	}
	
	private void assignWarrior(Entity entity){
		
	}
	
	private void assignProtector(Entity entity){
		
	}
	
	private void assignCombatEngineer(Entity entity){
		
	}
	
	private void assignCheerleader(Entity entity){
		
	}
	
	private void assignChief(Entity entity){
		int hp = 200;
		entity.setMaxHP(hp);
		entity.setCurrentHP(hp);
		entity.setStrength(30);
		entity.setAgility(2);
		entity.setDefense(15);
		entity.setAttackName("Command");
		this.name = Professions.CHIEF.getCharacterName();
		this.description = "Leader of Alien Team";
	}
	
	private void assignSpawn(Entity entity){
		int hp = 50;
		entity.setMaxHP(hp);
		entity.setCurrentHP(hp);
		entity.setStrength(10);
		entity.setAgility(1);
		entity.setDefense(10);
		entity.setAttackName("Fight");
		this.name = Professions.SPAWN.getCharacterName();
		this.description = "Base member of Alien Team";
	}
	
	private void assignLadyLisa(Entity entity){
		
	}
	
	private void assignWitch(Entity entity){
		
	}
	
	private void assignGoblin(Entity entity){
		
	}
	
	private void assignSniper(Entity entity){
		
	}
	
	private void assignTroll(Entity entity){
		
	}
	
	private void assignDragon(Entity entity){
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	

}
