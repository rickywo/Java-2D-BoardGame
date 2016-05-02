package model.gameModel.skills;
import model.gameModel.Entity;

public class GreatWall extends Command {
    private int oldDefense;
	private int amount;
    private Entity target;

    public GreatWall(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
    	oldDefense = target.getDefense();
        System.out.println("Defense Increase:" + amount);
        target.beDefended(amount);
        System.out.println("Defense rose from " + oldDefense + " to " + 
        		target.getDefense());
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
        return "Great Wall " + target.getName();
    }

	@Override
	public void execute(Entity[] targets) {
	}
}
