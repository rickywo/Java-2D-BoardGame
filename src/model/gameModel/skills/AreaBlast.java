package model.gameModel.skills;

import model.gameModel.Entity;

public class AreaBlast extends Command {
	
	private int damage;
	private Entity[] targets;
	
	public AreaBlast(int damage){
		this.damage = damage;
	}
	
  @Override
    public void execute(Entity[] targets) {
        //target.setVisibility(Visibility.INVISIBLE);
	  	this.targets = targets;
        System.out.println("Damage:" + damage);
        for(Entity target : targets) {
        	target.beAttacked(damage);
        }
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
       for(Entity target : targets){
    	   string.concat(target.getName() + " ");
       }
    	return "Area Blast " + string;
    }

	@Override
	public void execute(Entity target) {
		
		
	}
}
