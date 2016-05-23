package model.gameModel;

/**
 * Created by Human v Alien Team on 2016/5/23.
 */


public interface AttackeeInterface {

    void beAttacked(int damage);

    void beHealed (int amount);

    void beDefended (int amount);

    void beStrengthened(int amount);

    void beCheered(int strengthAmt, int defenseAmt, int agilityAmt);

    void beDefenseAttacked(int damage);

    void beStrengthAttacked(int damage);
}
