package model.gameModel.skills;
import model.gameModel.Entity;

public class CheerDance extends Command {

    private int strengthAmt;
    private int defenseAmt;
    private int agilityAmt;

    public CheerDance(int strengthAmt, int defenseAmt, int agilityAmt) {
        this.strengthAmt = strengthAmt;
        this.defenseAmt = defenseAmt;
        this.agilityAmt = agilityAmt;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        oldDex = target.getAgility();
        target.beCheered(strengthAmt, defenseAmt, agilityAmt);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setStrength(oldStr);
            target.setDefense(oldDef);
            target.setAgility(oldDex);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
        }
    }

    @Override
    public String toString() {
        return "Cheer " + target.getName();
    }
}
