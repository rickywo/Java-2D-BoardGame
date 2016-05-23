/*
 * Copyright (C) 2016 Ricky Wu.
 */
package model.gameModel;

import resources.Consts;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Created by Human v Alien Team on 2016/4/29.
 */
public class Team implements TeamInterface {

    /** The Constant NUM_PIECES_PER_TEAM. */
    private static final int NUM_PIECES_PER_TEAM = Consts.NUM_PIECES_PER_TEAM;
    
    /** The team members. */
    private ArrayList<Entity> members;
    
    /** The race name. */
    private String race = null;;
    
    /** The type. */
    private TeamTypes type;

    /**
     * Instantiates a new team.
     *
     * @param type the type
     */
    public Team(TeamTypes type) {
        this.type = type;
        members = new ArrayList<Entity>();
        initialise();
    }

    /**
     * Initialise.
     * To initialize team members
     */
    @Override
    public void initialise() {

        EntityFlyweightFactory fwFactory = GameBoard.fwFactory;
        Entity entity;

        switch (type) {
            case Human:
                race = Consts.HUMAN;
                for(int i=1; i<NUM_PIECES_PER_TEAM+1; i++){
                    entity = fwFactory.createEntity(ProfessionTypes.SOLDIER);
                    entity.setName(race + i);
                    if(i == 1) entity = fwFactory.createProfessionalEntity(ProfessionTypes.COMMANDER, entity);
                    members.add(entity);
                }
                break;
            case Alien:
                race = Consts.ALIEN;
                for(int i=1; i<NUM_PIECES_PER_TEAM+1; i++){
                    entity = fwFactory.createEntity(ProfessionTypes.SPAWN);
                    entity.setName(race + i);
                    if(i == 1) entity = fwFactory.createProfessionalEntity(ProfessionTypes.CHIEF, entity);
                    members.add(entity);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return race;
    }

    /**
     * Size.
     *
     * @return the size of a team
     */
    public int size() {
        return members.size();
    }

    /**
     * Gets a entity at [index]
     *
     * @param i the i
     * @return the entity
     */
    public Entity get(int i) {
        return members.get(i);
    }

    /**
     * Removes a entity object from its team member
     *
     * @param e the e
     */
    public void remove(Entity e) {
        members.remove(e);
    }

    /**
     * Gets all members.
     *
     * @return Arraylist of entities
     */
    @Override
    public ArrayList<Entity> getMembers() {
        return members;
    }
    
    /**
     * Sets team members.
     *
     * @param members An arraylist contains new members
     */
    public void setMembers(ArrayList<Entity> members) {
    	this.members = members;
    }

    /**
     * Sets entity moved flag to false for all members.
     */
    @Override
    public void resetTeamMoved() {
        for(Entity e: members) {
            e.unsetMoved();
        }
    }

    /**
     * Sets entity moved flag to true for all members.
     */
    @Override
    public void setTeamMoved() {
        for(Entity e: members) {
            e.setMoved();
        }
    }

    /**
     * Checks if is teams turn finished.
     *
     * @return true, if is teams turn finished
     */
    @Override
    public boolean isTeamsTurnFinished() {
        // Change turn if any piece in the team is moved
        for(Entity e: members) {
            if(e.isMoved()) return true;
        }
        return false;
    }

    /**
     * Gets the entity by its cooridinates.
     *
     * @param x the x
     * @param y the y
     * @return the entity by xy
     */
    public Entity getEntityByXY(int x, int y) {
        for(Entity e: members) {
            if(e.getXPos() == x && e.getYPos() == y) {
                return e;
            }
        }
        return null;
    }

    /**
     * Sets the entity by a specific cooridinates.
     *
     * @param x the x
     * @param y the y
     * @param entity the entity
     */
    public void setEntityByXY(int x, int y, Entity entity) {
        for(int i = 0 ; i < members.size() ; i ++) {
            Entity e = members.get(i);
            int ox = e.getXPos();
            int oy = e.getYPos();
            if(ox == x && oy == y) {
                if(entity.getXPos() != ox ||
                        entity.getYPos() != oy) {
                    entity.setPos(ox, oy);
                }
                members.remove(i);
                members.add(i, entity);

             }
        }
    }

    /**
     * Checks if is team defeated.
     *
     * @return true, if is team defeated
     */
    public boolean isTeamDefeated() {
        int numPiecesDead = 0;
        Entity leader = members.get(0);
        if(leader.getXPos() == -1 && leader.getYPos() == -1) {
            // Leader is killed
            return true;
        }

        for(Entity e: members) {
            if(e.getXPos() == -1 && e.getYPos() == -1) {
                numPiecesDead ++;
            }
        }

        if(numPiecesDead == members.size()) {
            return true;
        }

        return false;
    }
}
