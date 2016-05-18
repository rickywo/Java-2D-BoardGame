package model.gameModel.skills;

import model.gameModel.Entity;

/**
 * Created by Human v Alien Team on 2016/4/30.
 * Command pattern:
 * To encapsulates all requests as objects to provides
 * more functionality to a client.
 * Client: Entity.class and its subclasses
 * Command object can be created in client instance
 * then encapsulate parameters that needed for an
 * command. It includes previous state of a target
 * object and reference of target itself.
 *
 */

public abstract class Command {

    int oldHp;
    int oldStr;
    int oldDef;
    int oldDex;

    Entity target;


    public abstract void execute(Entity target);

    public abstract void undo();

    public abstract void redo();

    @Override
    public abstract String toString();
}
