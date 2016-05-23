package model.gameModel.skills;

import model.gameModel.Entity;

public class LongLaser extends Command {
    private int damage;
    private Entity target;

    public LongLaser(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
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
        return "Long Laser " + target.getName();
    }
}
