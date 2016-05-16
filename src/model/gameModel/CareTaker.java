package model.gameModel;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by blahblah Team on 2016/5/16.
 */
public class CareTaker {

    protected Deque<MementoInterface> undoStack = new LinkedList<MementoInterface>();

    public MementoInterface retrieveMemento(){
        MementoInterface m = undoStack.pollLast();

        return m;
    }

    public void saveMemento(MementoInterface memento){
        undoStack.offerLast(memento);
    }
}
