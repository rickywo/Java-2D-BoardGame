package model.gameModel.skills;

import model.gameModel.Entity;
import model.gameModel.EntityInterface;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Human v Alien Team on 2016/4/30.
 */
public abstract class ProfessionDecorator extends Entity {

    private Entity entity;

    private Deque<Command> undoStack = new LinkedList<Command>();
    private Deque<Command> redoStack = new LinkedList<Command>();


    public ProfessionDecorator(String name, Entity entity) {
        super(name);
        this.entity = entity;
    }

    public void invokeSkill(Command command, Entity target) {
        System.out.println(this + " invoke " + command + " at " + target);
        command.execute(target);
        undoStack.offerLast(command);
    }

    public void attack(Entity target) {
        invokeSkill(new Attack(), target);
    }

    /**
     * Undo
     */
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
    public void redoLastInvoke() {
        if (!redoStack.isEmpty()) {
            Command previousInvoke = redoStack.pollLast();
            undoStack.offerLast(previousInvoke);
            System.out.println(this + " redoes " + previousInvoke);
            previousInvoke.redo();
        }
    }

    @Override
    public int calculateSteps(int steps) {
        return steps * entity.getAgility();
    }

    @Override
    public void beAttacked(int damage) {
        setCurrentHP(damage - entity.getDefense());
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
