package model.gameModel;


import model.gameModel.jobs.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Human v Alien Team on 2016/4/29.
 *
 * Flyweight pattern, It minimizes memory use by sharing object
 * instances. It holds a map of entity instances and new entities are
 * created only when none of the type already exists.
 *
 * In this class, the factory creates a prototype of a profession class.
 * A copy of a profession class will be cloned by a prototype instance
 * whenever it is needed.
 * It saves time to recreate a new entity from scratch.
 *
 */

public class EntityFlyweightFactory {

    
    private static final String JOB_CLASS_URL_PREFIX = "model.gameModel.jobs.";

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
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                Class<Entity> _tempClass = (Class<Entity>) classLoader.loadClass(JOB_CLASS_URL_PREFIX + type.getCharacterName());
                Constructor<Entity> e = _tempClass.getDeclaredConstructor(String.class, Entity.class);
                prototype = e.newInstance(type.getCharacterName(), soldier);
                entities.put(type, prototype);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
        return (Entity) prototype.clone();
    }
}
