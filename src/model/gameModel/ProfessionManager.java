/*
 * Copyright (C) 2016 Ricky Wu.
 */
package model.gameModel;

import resources.Consts;

/**
 * Created by Human v Alien Team on 2016/5/2.
 */
public class ProfessionManager {

    /**
     * Change profession.
     *
     * @param target the original entity to be transfered
     * @param weapon the weapon it picked up
     * @return ProfessionDecorator the advanced professional entiy
     */
    public static ProfessionDecorator changeProfession(Entity target, Weapon.Weapons weapon) {
        EntityFlyweightFactory fwFactory = GameBoard.fwFactory;
        ProfessionTypes type = null;
        switch(weapon) {
            case CANNON:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.AREAATTACKER;
                else
                    type = ProfessionTypes.SNIPER;
                break;
            case GUN:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.WARRIOR;
                else
                    type = ProfessionTypes.TROLL;
                break;
            case MAGICALHANDS:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.MEDIC;
                else
                    type = ProfessionTypes.WITCH;
                break;
            case SHIELD:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.DEFENDER;
                else
                    type = ProfessionTypes.LADYLISA;
                break;
            case COMBATKIT:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.COMBATENGINEER;
                else
                    type = ProfessionTypes.DRAGON;
                break;
            case FLAG:
                if(target.getTeam() == Consts.HUMAN_TEAM_NUM)
                    type = ProfessionTypes.CHEERLEADER;
                else
                    type = ProfessionTypes.GOBLIN;
                break;
        }
        return fwFactory.createProfessionalEntity(type, target);
    }
}
