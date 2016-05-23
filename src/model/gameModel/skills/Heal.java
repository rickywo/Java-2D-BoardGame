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
		target.beHealed(amount);
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
        return "Heal " + target.getName();
    }
}
