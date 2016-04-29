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

    private final Map<ProfessionNames, Entity> entities;

    public EntityFlyweightFactory() {
        entities = new EnumMap<ProfessionNames, Entity>(ProfessionNames.class);
    }

    Entity createEntity(ProfessionNames type) {
        Entity entity = entities.get(type);
        if (entity == null) {
            switch (type) {
                case COMMANDER:
                    entity = new Commander(ProfessionNames.COMMANDER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case SOLDIER:
                    entity = new Soldier(ProfessionNames.SOLDIER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case MEDIC:
                    entity = new Medic(ProfessionNames.MEDIC.getCharacterName());
                    entities.put(type, entity);
                    break;
                case AREAATTACKER:
                    entity = new AreaAttacker(ProfessionNames.AREAATTACKER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case WARRIOR:
                    entity = new Warrior(ProfessionNames.WARRIOR.getCharacterName());
                    entities.put(type, entity);
                    break;
                case DEFENDER:
                    entity = new Defender(ProfessionNames.DEFENDER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case COMBATENGINEER:
                    entity = new CombatEngineer(ProfessionNames.COMBATENGINEER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case CHEERLEADER:
                    entity = new Cheerleader(ProfessionNames.CHEERLEADER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case CHIEF:
                    entity = new Chief(ProfessionNames.CHIEF.getCharacterName());
                    entities.put(type, entity);
                    break;
                case SPAWN:
                    entity = new Spawn(ProfessionNames.SPAWN.getCharacterName());
                    entities.put(type, entity);
                    break;
                case LADYLISA:
                    entity = new LadyLisa(ProfessionNames.LADYLISA.getCharacterName());
                    entities.put(type, entity);
                    break;
                case WITCH:
                    entity = new Witch(ProfessionNames.WITCH.getCharacterName());
                    entities.put(type, entity);
                    break;
                case GOBLIN:
                    entity = new Goblin(ProfessionNames.GOBLIN.getCharacterName());
                    entities.put(type, entity);
                    break;
                case SNIPER:
                    entity = new Sniper(ProfessionNames.SNIPER.getCharacterName());
                    entities.put(type, entity);
                    break;
                case TROLL:
                    entity = new Troll(ProfessionNames.TROLL.getCharacterName());
                    entities.put(type, entity);
                    break;
                case DRAGON:
                    entity = new Dragon(ProfessionNames.DRAGON.getCharacterName());
                    entities.put(type, entity);
                    break;
                default:
                    break;
            }
        }
        return entity;
    }
}
