package model.gameModel;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Human v Alien Team on 2016/5/16.
 * Memento behavioral design pattern:
 * CareTaker class
 * Responsibility:
 * 1. To save snapshots
 * 2. To provide a interface for client to retrieve
 *    previous saved snapshot for state recovering
 *
 * Caretaker cannot et the information encapsulated in
 * the memento object.
 *
 */

public class CareTaker {

    protected Deque<MementoInterface> undoStack = new LinkedList<MementoInterface>();

    public MementoInterface retrieveMemento(){
        if (!undoStack.isEmpty()) {
            MementoInterface m = undoStack.pollLast();
            return m;
        }
        return null;
    }

    public void saveMemento(MementoInterface memento){
        undoStack.offerLast(memento);
    }
}
