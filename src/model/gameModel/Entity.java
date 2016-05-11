package model.gameModel;

import model.gameModel.skills.Attack;
import model.gameModel.skills.Command;
import model.gameModel.skills.EntityActionInterface;
import model.gameModel.skills.Move;

import java.util.Deque;
import java.util.LinkedList;

public class Entity implements EntityInterface, EntityActionInterface, Cloneable {

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
	private boolean upgradable;
    private int attackRange; // Range of attack
	private boolean moved;
	private Weapon weapon;
	private int xPos;	// x coordinates
	private int yPos; //y coords

	protected Deque<Command> undoStack = new LinkedList<Command>();
	protected Deque<Command> redoStack = new LinkedList<Command>();

	
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
		return steps * agility;
	}

	@Override
	public void beAttacked(int damage) {
		setCurrentHP(getCurrentHP() - (damage - calculateDefenceFactor()));
	}
	
	
	@Override
	public void beHealed(int amount) {
		setCurrentHP(getCurrentHP() + amount);
		if(getCurrentHP() > getMaxHP()) {
			setCurrentHP(getMaxHP());
		}
	}
	
	@Override
	public void beDefended(int amount) {
		setDefense(getDefense() + amount);
	}
	
	@Override
	public void beStrengthened(int amount){
		setStrength(getStrength() + amount);
	}
	
	@Override
	public void beCheered(int strengthAmt, int defenseAmt, int agilityAmt) {
		setStrength(getStrength() + strengthAmt);
		setDefense(getDefense() + defenseAmt);
		setAgility(getAgility() + agilityAmt);
	}
	
	@Override
	public void beDefenseAttacked(int damage) {
		setDefense(getDefense() - damage);
		if(getDefense() < 0){
			setDefense(0);
		}
	}
	
	@Override
	public void beAgilityAttacked(int damage){
		setAgility(getAgility() - damage);
		if(getAgility() < 0){
			setAgility(0);
		}
	}
	
	@Override
	public void beStrengthAttacked(int damage){
		setStrength(getStrength() - damage);
		if(getStrength() < 0){
			setAgility(0);
		}
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

	public boolean isUpgradable() {
		return upgradable;
	}

	public void setUpgradable(boolean upgradable) {
		this.upgradable = upgradable;
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

	protected int calculateDamage() {
		int damage = getStrength();
		return damage;
	}

	protected int calculateDefenceFactor() {
		int factor = (getDefense() / 5);
		return factor;
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
	public void attack(Entity target) {
		int damage = calculateDamage();
		Command attack = new Attack(damage);
		attack.execute(target);
		setMoved();
		undoStack.offerLast(attack);
	}

	@Override
	public void moveTo(Entity target, int x, int y) {
		Command move = new Move(x, y);
		move.execute(target);
		setMoved();
		undoStack.offerLast(move);
	}

	@Override
	public void invokeSkill(Command command, Entity target) {

	}

	/**
	 * Undo
	 */
	@Override
	public void undoLastInvoke() {
		if (!undoStack.isEmpty()) {
			Command previousInvoke = undoStack.pollLast();
			redoStack.offerLast(previousInvoke);
			System.out.println(this + " undoes " + previousInvoke);
			previousInvoke.undo();
		}
	}

	/**
	 * Redo
	 */
	@Override
	public void redoLastInvoke() {
		if (!redoStack.isEmpty()) {
			Command previousInvoke = redoStack.pollLast();
			undoStack.offerLast(previousInvoke);
			System.out.println(this + " redoes " + previousInvoke);
			previousInvoke.redo();
		}
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
