package model.gameModel.skills;

import model.gameModel.Entity;

public class EyesOfStone extends Command {

    private Entity target;
    private int remainder;

    public EyesOfStone(int remainder) {
    	this.remainder = remainder;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldHp = target.getCurrentHP();
        target.setCurrentHP(remainder);
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
    	return "Eyes of Stone " + target.getName();
    }

}

