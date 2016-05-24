/*
 * Copyright (C) 2016 Ricky Wu.
 */
package model.gameModel.skills;

import model.gameModel.Entity;
import model.gameModel.InvokeObservableInterface;
import model.gameModel.ObservationSubject;

// TODO: Auto-generated Javadoc
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

public abstract class Command implements InvokeObservableInterface {

    /** The previous hp. */
    int oldHp;
    
    /** The previous strength. */
    int oldStr;
    
    /** The previous defence value. */
    int oldDef;
    
    /** The previous agility value. */
    int oldDex;

    /** The target entity that command invokes on. */
    Entity target;

    /** The ObservationSubject reference */
    ObservationSubject subject;


    /**
     * Execute.
     *
     * @param target the target
     */
    public abstract void execute(Entity target);

    /**
     * Undo.
     */
    public abstract void undo();

    /**
     * Redo.
     */
    public abstract void redo();

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public abstract String toString();
}
