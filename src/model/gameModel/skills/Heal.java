package model.gameModel.skills;
import model.gameModel.Entity;

public class Heal extends Command {
	
	private int oldHP;
	private int amount;
	private Entity target;
	
	public Heal(int amount){
		this.amount = amount;
	}
	
	@Override
	public void execute(Entity target) {
		this.target = target;
		oldHP = target.getCurrentHP();
		System.out.println("HP Increase: " + amount);
		target.beHealed(amount);
		System.out.println("HP rose from " + oldHP + " to " 
				+ target.getCurrentHP());
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
        return "Heal " + target.getName();
    }

	@Override
	public void execute(Entity[] targets) {
	}

}
