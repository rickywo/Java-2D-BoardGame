package model.gameModel.skills;
import model.gameModel.Entity;

public class CheerDance extends Command {

    private int strengthAmt;
    private int defenseAmt;
    private int agilityAmt;
    private Entity target;

    public CheerDance(int strengthAmt, int defenseAmt, int agilityAmt) {
        this.strengthAmt = strengthAmt;
        this.defenseAmt = defenseAmt;
        this.agilityAmt = agilityAmt;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        System.out.println("Strength rose by:" + strengthAmt);
        System.out.println("Defense rose by:" + defenseAmt);
        System.out.println("Agility rose by:" + agilityAmt);
        target.beCheered(strengthAmt, defenseAmt, agilityAmt);
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
        return "Cheer " + target.getName();
    }
}
