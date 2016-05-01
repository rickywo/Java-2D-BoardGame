package model.gameModel.skills;

import model.gameModel.Entity;

/**
 * Created by blahblah Team on 2016/4/30.
 */
public class Attack extends Command {

    private Entity target;

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
        this.target = target;
    }

    @Override
    public void undo() {
        if (target != null) {
            //target.setVisibility(Visibility.VISIBLE);
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
        return "Invisibility spell";
    }
}
