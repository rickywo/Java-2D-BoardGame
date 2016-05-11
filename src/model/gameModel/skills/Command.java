package model.gameModel.skills;

import model.gameModel.Entity;

/**
 * Created by Human v Alien Team on 2016/4/30.
 */
public abstract class Command {

    public abstract void execute(Entity target);

    public abstract void undo();

    public abstract void redo();

    @Override
    public abstract String toString();
}
