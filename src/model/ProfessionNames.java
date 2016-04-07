package model;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public enum ProfessionNames {
    COMMANDER("Commander"),
    SOLDIER("Soldier"),
    MEDIC("Medic"),
    AREAATTACKER("AreaAttacker"),
    WARRIOR("Warrior"),
    PROTECTOR("Protector"),
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
    ProfessionNames(String charName) {
        this.characterName = charName;
    }

    public String getCharacterName() {
        return this.characterName;
    }
}