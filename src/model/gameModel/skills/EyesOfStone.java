package model.gameModel.skills;

import model.gameModel.Entity;

//TODO - makes enemies retreat
public class EyesOfStone extends Command {
    private int steps;
    private Entity[] targets;

    public EyesOfStone(int steps) {
        this.steps = steps;
    }

    @Override
    public void execute(Entity[] targets) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.targets = targets;
        System.out.println("Enemies retreat by " + steps + " steps");
        //target.beAttacked(damage);
        
    }

    @Override
    public void undo() {
        if (targets != null) {
            //target.setVisibility(Visibility.VISIBLE);
        }
    }

    @Override
    public void redo() {
        if (targets != null) {
            //target.setVisibility(Visibility.INVISIBLE);
        }
    }

    @Override
    public String toString() {
    	String string = "";
        for(Entity target : targets) {
        	string.concat(target.getName() + " ");
        }
    	return "Eyes of Stone " + string;
    }

	@Override
	public void execute(Entity target) {
	}
}

