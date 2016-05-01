package model.gameModel;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public enum ProfessionTypes {

    COMMANDER("Commander"),
    SOLDIER("Soldier"),
    MEDIC("Medic"),
    AREAATTACKER("AreaAttacker"),
    WARRIOR("Warrior"),
    DEFENDER("Defender"),
    COMBATENGINEER("CombatEngineer"),
    CHEERLEADER("CheerLeader"),
    CHIEF("Chief"),
    SPAWN("Spawn"),
    LADYLISA("LadyLisa"),
    WITCH("Witch"),
    GOBLIN("Goblin"),
    SNIPER("Sniper"),
    TROLL("Troll"),
    DRAGON("Dragon");

    private String characterName;
    ProfessionTypes(String charName) {
        this.characterName = charName;
    }

    public String getCharacterName() {
        return this.characterName;
    }
}