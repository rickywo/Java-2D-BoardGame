package model.gameModel.skills;

import model.gameModel.Entity;

//TODO - surrounds enemies with lightning quick speed
public class HigherPower extends Command {
	
    private Entity[] targets;

    public HigherPower() {
    }

    @Override
    public void execute(Entity[] targets) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.targets = targets;
        System.out.println("Surrounding enemies");
//        target.beAttacked(damage);
        
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
    	return "Higher Power " + string;
    }

	@Override
	public void execute(Entity target) {
	}
}
