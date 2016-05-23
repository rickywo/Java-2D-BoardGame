package model.gameModel.skills;

import model.gameModel.Entity;

/**
 * Created by blahblah Team on 2016/4/30.
 */
public class Attack extends Command {

    private int damage;

    public Attack(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
        oldHp = target.getCurrentHP();
        target.beAttacked(damage);
        this.target = target;
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
        return "Attack " + target.getName();
    }
}
