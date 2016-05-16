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
        //target.setVisibility(Visibility.INVISIBLE);
        oldHp = target.getCurrentHP();

        System.out.println("Damage:" + damage);
        target.beAttacked(damage);
        this.target = target;
    }

    @Override
    public void undo() {
        if (target != null) {
            //target.setVisibility(Visibility.VISIBLE);
            target.setCurrentHP(oldHp);

            System.out.println("Restore attack");
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            //target.setVisibility(Visibility.INVISIBLE);
        }
    }

    @Override
    public String toString() {
        return "Attack " + target.getName();
    }
}
