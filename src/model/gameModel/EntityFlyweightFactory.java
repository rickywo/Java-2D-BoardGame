package model.gameModel;


import model.gameModel.jobs.*;

import java.util.EnumMap;
import java.util.Map;
/**
 * Created by Human v Alien Team on 2016/4/29.
 */
public class EntityFlyweightFactory {

    

    /**
     *
     * EntityFlyweightFactory. It minimizes memory use by sharing object
     * instances. It holds a map of entity instances and new entities are
     * created only when none of the type already exists.
     *
     */

    private final Map<ProfessionTypes, Entity> entities;
    // Leave ProfessionTypes here for future extension

    public EntityFlyweightFactory() {
        entities = new EnumMap<ProfessionTypes, Entity>(ProfessionTypes.class);
    }

    Entity createEntity(ProfessionTypes type) {
        Entity prototype = entities.get(type);
        if (prototype == null) {
            switch (type) {
                case SOLDIER:
                    prototype = new Soldier(ProfessionTypes.SOLDIER.getCharacterName());
                    entities.put(type, prototype);
                    break;
                case SPAWN:
                    prototype = new Spawn(ProfessionTypes.SPAWN.getCharacterName());
                    entities.put(type, prototype);
                    break;
                default:
                    break;
            }
        }
        return (Entity) prototype.clone();
    }

    Entity createProfessionalEntity(ProfessionTypes type, Entity soldier) {
        Entity prototype = entities.get(type);
        if (prototype == null) {
            switch (type) {
                case COMMANDER:
                    prototype = new Commander(ProfessionTypes.COMMANDER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case MEDIC:
                    prototype = new Medic(ProfessionTypes.MEDIC.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case AREAATTACKER:
                    prototype = new AreaAttacker(ProfessionTypes.AREAATTACKER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case WARRIOR:
                    prototype = new Warrior(ProfessionTypes.WARRIOR.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case DEFENDER:
                    prototype = new Defender(ProfessionTypes.DEFENDER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case COMBATENGINEER:
                    prototype = new CombatEngineer(ProfessionTypes.COMBATENGINEER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case CHEERLEADER:
                    prototype = new Cheerleader(ProfessionTypes.CHEERLEADER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case CHIEF:
                    prototype = new Chief(ProfessionTypes.CHIEF.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;

                case LADYLISA:
                    prototype = new LadyLisa(ProfessionTypes.LADYLISA.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case WITCH:
                    prototype = new Witch(ProfessionTypes.WITCH.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case GOBLIN:
                    prototype = new Goblin(ProfessionTypes.GOBLIN.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case SNIPER:
                    prototype = new Sniper(ProfessionTypes.SNIPER.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case TROLL:
                    prototype = new Troll(ProfessionTypes.TROLL.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                case DRAGON:
                    prototype = new Dragon(ProfessionTypes.DRAGON.getCharacterName(), soldier);
                    entities.put(type, prototype);
                    break;
                default:
                    break;
            }
        }
        return (Entity) prototype.clone();
    }
}
