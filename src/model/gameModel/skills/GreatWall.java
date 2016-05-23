package model.gameModel.skills;
import model.gameModel.Entity;

public class GreatWall extends Command {
	private int amount;
    private Entity target;

    public GreatWall(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
    	oldDef = target.getDefense();
        target.beDefended(amount);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setDefense(oldDef);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
        }
    }

    @Override
    public String toString() {
        return "Great Wall " + target.getName();
    }
}
