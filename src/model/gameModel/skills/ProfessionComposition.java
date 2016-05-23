package model.gameModel.skills;

import model.gameModel.Entity;

/**
 * Created by Human v Alien Team on 2016/4/30.
 * Decorator design pattern: To decorator Entity
 * Class to meet more functionality requirements.
 *
 * In our assignment, advance professional has special
 * ability that is different to basic attack function.
 * Initially, all pieces in a team are basic unit "Soldier"
 * or "Spawn". Basic unit dynamically upgrade to advanced
 * professional class when picked up a specific weapon on
 * the map. So what we need here includes:
 *
 * 1. Basic invoke function is still needed for Entity class
 * 2. More abilities are able to attach to an entity dynamically
 *
 * In order to achieve it, Composition design pattern is
 * implemented here. All advanced professionals extends
 * ProfessionComposition class and implement abstract method
 * "Invoke(Entity target)" for getting its dedicate skill
 * work.
 */


public abstract class ProfessionComposition extends Entity {

    private Entity entity;

    public ProfessionComposition(String name, Entity entity) {
        super(name);
        this.entity = entity;
    }

    public abstract void invoke(Entity target);

    public void invokeSkill(Command command, Entity target) {
        command.execute(target);
        entity.undoStack.offerLast(command);
        setMoved();
    }
    
    public void invokeSkill(Command command, Entity[] targets) {
    	for(Entity target : targets){
    		command.execute(target);
    	}
        entity.undoStack.offerLast(command);
        setMoved();
    }

    public void attack(Entity target) {

        int damage = calculateDamage();
        invokeSkill(new Attack(damage), target);
        setMoved();
    }

    /**
     * Undo
     */
    public void undoLastInvoke() {
        if (!undoStack.isEmpty()) {
            Command previousInvoke = entity.undoStack.pollLast();
            entity.redoStack.offerLast(previousInvoke);
            previousInvoke.undo();
        }
    }

    /**
     * Redo
     */
    public void redoLastInvoke() {
        if (!redoStack.isEmpty()) {
            Command previousInvoke = entity.redoStack.pollLast();
            entity.undoStack.offerLast(previousInvoke);
            previousInvoke.redo();
        }
    }

    @Override
    public int calculateSteps(int steps) {
        return steps * entity.getAgility();
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
        return entity.getTeam();
    }

    public void setTeam(int team) {
        entity.setTeam(team);
    }

    public String getName(){
        return entity.getName();
    }

    public void setName(String name){
        entity.setName(name);
    }

    public String getProfessionName(){
        return entity.getProfessionName();
    }

    public void setProfessionName(String name){
        entity.setProfessionName(name);
    }

    public String getDescription(){
        return entity.getDescription();
    }

    public void setDescription(String descrip){
        entity.setDescription(descrip);
    }

    public int getStrength() {
        return entity.getStrength();
    }

    public void setStrength(int strength) {
        entity.setStrength(strength);
    }

    public int getAgility() {
        return entity.getAgility();
    }

    public void setAgility(int agility) {
        entity.setAgility(agility);
    }

    public int getMaxHP() {
        return entity.getMaxHP();
    }

    public void setMaxHP(int hp) {
        entity.setMaxHP(hp);
    }

    public int getCurrentHP() {
        return entity.getCurrentHP();
    }

    public void setCurrentHP(int hp) {
        entity.setCurrentHP(hp);
    }

    public int getDefense() {
        return entity.getDefense();
    }

    public void setDefense(int defense) {
        entity.setDefense(defense);
    }

    public String getAttackName(){
        return entity.getAttackName();
    }

    public void setAttackName(String name){
        entity.setAttackName(name);
    }

    @Override
    public void setAttackRange(int range) {
        entity.setAttackRange(range);
    }

    @Override
    public int getAttackRange() {
        return entity.getAttackRange();
    }

    public void setPos(int x, int y) {
        entity.setPos(x, y);
    }

    public int getXPos(){
        return entity.getXPos();
    }

    public int getYPos(){
        return entity.getYPos();
    }

    public void setMoved() {
        entity.setMoved();
    }

    public void unsetMoved() {
        entity.unsetMoved();
    }

    public boolean isMoved() {
        return entity.isMoved();
    }

    @Override
    public String toString() {
        return entity.getName() + ";" +
                entity.getProfessionName() + ";" +
                entity.getMaxHP() + ";" +
                entity.getCurrentHP() + ";" +
                entity.getStrength() +  ";" +
                entity.getAgility() + ";" +
                entity.getDefense();
    }
}
