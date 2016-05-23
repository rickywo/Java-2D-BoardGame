package model.gameModel.skills;

import model.gameModel.Entity;

public class AllIn extends Command {

    private int damage;

    public AllIn(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
    	this.target = target;
        oldHp = target.getCurrentHP();
        target.beAttacked(damage);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setCurrentHP(oldHp);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
        }
    }

    @Override
    public String toString() {
        return "All In " + target.getName();
    }
}
