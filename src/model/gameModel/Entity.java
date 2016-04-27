package model.gameModel;

public abstract class Entity implements EntityInterface, Cloneable{

	private String name;
	private int team; // Camp of this unit belongs to
	private String professionName;
	private String description;
	private int strength; // An attribute (strength) for causing damage
	private int agility; // An attribute (agility) for moving on the grid map
	private int currentHP; // HP
	private int maxHP; //max HP
	private int defense; // Defence
	private String attackName; //name of primary attack
    private int attackRange; // Range of attack
	private boolean moved;
	private Weapon weapon;
	private int xPos;	// x coordinates
	private int yPos; //y coords

	
	public Entity(String name){
		this.name = name;
		moved = false;
		weapon = null;
	}
	
	@Override
	public Object clone(){
		try{
			return super.clone();
		} catch(Exception e){
			return null;
		}
	}

	@Override
	public int calculateSteps(int steps) {
		return steps;
	}

	@Override
	public int attack() {
		return 0;
	}

	@Override
	public void beAttacked(int damage) {
		
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getProfessionName(){
		return professionName;
	}
	
	public void setProfessionName(String name){
		this.professionName = name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String descrip){
		this.description = descrip;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int hp) {
		this.maxHP = hp;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String getAttackName(){
		return attackName;
	}

    public void setAttackName(String name){
		this.attackName = name;
	}

    @Override
    public void setAttackRange(int range) {
        this.attackRange = range;
    }

    @Override
    public int getAttackRange() {
        return this.attackRange;
    }
	
	public void setPos(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}

	public void setMoved() {
		moved = true;
	}

	public void unsetMoved() {
		moved = false;
	}

	public boolean isMoved() {
		return moved;
	}
	
	//remove later
	public void printAllAttributes(){
		System.out.println("Name: " + name);
		System.out.println("Team: " + team);
		System.out.println("MaxHP: " + maxHP);
		System.out.println("CurrentHP: " + currentHP);
		System.out.println("Strength: " + strength);
		System.out.println("Agility: " + agility);
		System.out.println("Defense: " + defense);
		System.out.println("Attack Name: " + attackName);
		System.out.println("Profession: " + professionName);
		System.out.println("Description: " + description);
		System.out.println("Weapon: " + weapon);
		System.out.println("Coordinates: " + xPos + ", " + yPos);
		System.out.println();
	}

	@Override
	public String toString() {
		return name + ";" +
                professionName + ";" +
                maxHP + ";" +
                currentHP + ";" +
                strength +  ";" +
                agility + ";" +
                defense;
	}
}
