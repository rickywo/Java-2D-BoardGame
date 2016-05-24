package model.gameModel.skills;
import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class CheerDance extends Command {

    private int strengthAmt;
    private int defenseAmt;
    private int agilityAmt;

    public CheerDance(int strengthAmt, int defenseAmt, int agilityAmt, ObservationSubject subject) {
        this.strengthAmt = strengthAmt;
        this.defenseAmt = defenseAmt;
        this.agilityAmt = agilityAmt;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        oldDex = target.getAgility();
        target.beCheered(strengthAmt, defenseAmt, agilityAmt);
        notifySubject(subject);
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

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.UPCAST);
    }
}
