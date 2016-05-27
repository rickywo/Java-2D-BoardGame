
package model.gameModel.skills;

import model.gameModel.*;

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
 * ProfessionDecorator class and implement abstract method
 * "Invoke(Entity target)" for getting its dedicate skill
 * work.
 */


public abstract class ProfessionDecorator extends Entity implements EntityInterface, AttackeeInterface, AttackerInterface {

    /** The basic entity of this advanced entity before convertion */
    private Entity entity;
    private final boolean upgradable = false;

    /**
     * Instantiates a new profession decorator.
     *
     * @param name the name
     * @param entity the entity
     */
    public ProfessionDecorator(String name, Entity entity) {
        super(name);
        this.entity = entity;
    }

    /**
     * Invoke.
     *
     * @param target the target
     */
    public abstract void invoke(Entity target, ObservationSubject subject);

    /**
     * Invoke skill.
     *
     * @param command the command
     * @param target the target
     */
    public void invokeSkill(Command command, Entity target) {
        command.execute(target);
        entity.undoStack.offerLast(command);
        setMoved();
    }

    /**
     * Attack.
     *
     * @param target the target
     */
    public void attack(Entity target, ObservationSubject subject) {

        int damage = calculateDamage();
        invokeSkill(new Attack(damage, subject), target);
    }

    @Override
    public void moveTo(Entity target, int x, int y) {
        invokeSkill(new Move(x, y), target);
    }

    /**
     * Undo.
     */
    public void undoLastInvoke() {

        if (!entity.undoStack.isEmpty()) {
            Command previousInvoke = entity.undoStack.pollLast();
            previousInvoke.undo();
        }
    }

    @Override
    public boolean isUpgradable() {
        return upgradable;
    }

    /**
     * Calculate steps.
     *
     * @param steps the steps
     * @return the int
     */
    @Override
    public int calculateSteps(int steps) {
        return steps * entity.getAgility();
    }

    /**
     * Be attacked.
     *
     * @param damage the damage
     */
    @Override
    public void beAttacked(int damage) {
        setCurrentHP(getCurrentHP() - (damage - calculateDefenceFactor()));
    }
    
	/**
	 * Be healed.
	 *
	 * @param amount the amount
	 */
	@Override
	public void beHealed(int amount) {
		setCurrentHP(getCurrentHP() + amount);
		if(getCurrentHP() > getMaxHP()) {
			setCurrentHP(getMaxHP());
		}
	}
	
	/**
	 * Be defended.
	 *
	 * @param amount the amount
	 */
	@Override
	public void beDefended(int amount) {
		setDefense(getDefense() + amount);
	}
	
	/**
	 * Be strengthened.
	 *
	 * @param amount the amount
	 */
	@Override
	public void beStrengthened(int amount){
		setStrength(getStrength() + amount);
	}
	
	/**
	 * Be cheered.
	 *
	 * @param strengthAmt the strength amt
	 * @param defenseAmt the defense amt
	 * @param agilityAmt the agility amt
	 */
	@Override
	public void beCheered(int strengthAmt, int defenseAmt, int agilityAmt) {
		setStrength(getStrength() + strengthAmt);
		setDefense(getDefense() + defenseAmt);
		setAgility(getAgility() + agilityAmt);
	}
	
	/**
	 * Be defense attacked.
	 *
	 * @param damage the damage
	 */
	@Override
	public void beDefenseAttacked(int damage) {
		setDefense(getDefense() - damage);
		if(getDefense() < 0){
			setDefense(0);
		}
	}
	
	/**
	 * Be strength attacked.
	 *
	 * @param damage the damage
	 */
	@Override
	public void beStrengthAttacked(int damage){
		setStrength(getStrength() - damage);
		if(getStrength() < 0){
			setAgility(0);
		}
	}

    /**
     * Gets the team.
     *
     * @return the team
     */
    public int getTeam() {
        return entity.getTeam();
    }

    /**
     * Sets the team.
     *
     * @param team the new team
     */
    public void setTeam(int team) {
        entity.setTeam(team);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName(){
        return entity.getName();
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name){
        entity.setName(name);
    }

    /**
     * Gets the profession name.
     *
     * @return the profession name
     */
    public String getProfessionName(){
        return entity.getProfessionName();
    }

    /**
     * Sets the profession name.
     *
     * @param name the new profession name
     */
    public void setProfessionName(String name){
        entity.setProfessionName(name);
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription(){
        return entity.getDescription();
    }

    /**
     * Sets the description.
     *
     * @param descrip the new description
     */
    public void setDescription(String descrip){
        entity.setDescription(descrip);
    }

    /**
     * Gets the strength.
     *
     * @return the strength
     */
    public int getStrength() {
        return entity.getStrength();
    }

    /**
     * Sets the strength.
     *
     * @param strength the new strength
     */
    public void setStrength(int strength) {
        entity.setStrength(strength);
    }

    /**
     * Gets the agility.
     *
     * @return the agility
     */
    public int getAgility() {
        return entity.getAgility();
    }

    /**
     * Sets the agility.
     *
     * @param agility the new agility
     */
    public void setAgility(int agility) {
        entity.setAgility(agility);
    }

    /**
     * Gets the max hp.
     *
     * @return the max hp
     */
    public int getMaxHP() {
        return entity.getMaxHP();
    }

    /**
     * Sets the max hp.
     *
     * @param hp the new max hp
     */
    public void setMaxHP(int hp) {
        entity.setMaxHP(hp);
    }

    /**
     * Gets the current hp.
     *
     * @return the current hp
     */
    public int getCurrentHP() {
        return entity.getCurrentHP();
    }

    /**
     * Sets the current hp.
     *
     * @param hp the new current hp
     */
    public void setCurrentHP(int hp) {
        entity.setCurrentHP(hp);
    }

    /**
     * Gets the defense.
     *
     * @return the defense
     */
    public int getDefense() {
        return entity.getDefense();
    }

    /**
     * Sets the defense.
     *
     * @param defense the new defense
     */
    public void setDefense(int defense) {
        entity.setDefense(defense);
    }

    /**
     * Gets the attack name.
     *
     * @return the attack name
     */
    public String getAttackName(){
        return entity.getAttackName();
    }

    /**
     * Sets the attack name.
     *
     * @param name the new attack name
     */
    public void setAttackName(String name){
        entity.setAttackName(name);
    }

    /**
     * Sets the attack range.
     *
     * @param range the new attack range
     */
    @Override
    public void setAttackRange(int range) {
        entity.setAttackRange(range);
    }

    /**
     * Gets the attack range.
     *
     * @return the attack range
     */
    @Override
    public int getAttackRange() {
        return entity.getAttackRange();
    }

    /**
     * Sets the pos.
     *
     * @param x the x
     * @param y the y
     */
    public void setPos(int x, int y) {
        entity.setPos(x, y);
    }

    /**
     * Gets the x pos.
     *
     * @return the x pos
     */
    public int getXPos(){
        return entity.getXPos();
    }

    /**
     * Gets the y pos.
     *
     * @return the y pos
     */
    public int getYPos(){
        return entity.getYPos();
    }

    /**
     * Sets the moved.
     */
    public void setMoved() {
        entity.setMoved();
    }

    /**
     * Unset moved.
     */
    public void unsetMoved() {
        entity.unsetMoved();
    }

    /**
     * Checks if is moved.
     *
     * @return true, if is moved
     */
    public boolean isMoved() {
        return entity.isMoved();
    }

    public Entity getEntity() {
        return entity;
    }

    /**
     * To string.
     *
     * @return the string
     */
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
