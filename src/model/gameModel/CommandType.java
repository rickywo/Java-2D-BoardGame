package model.gameModel;

/**
 * Created by Human v Alien Team on 2016/5/24.
 */

public enum CommandType {

    ATTACK, UPCAST, DOWNCAST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}