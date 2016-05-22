package model.gameModel;

import resources.Consts;

import java.util.ArrayList;

/**
 * Created by Human v Alien Team on 2016/4/29.
 */
public class Team implements TeamInterface {

    private static final int NUM_PIECES_PER_TEAM = Consts.NUM_PIECES_PER_TEAM;
    private ArrayList<Entity> members;
    private String race = null;;
    private TeamTypes type;

    public Team(TeamTypes type) {
        this.type = type;
        members = new ArrayList<Entity>();
        initialise();
        for (int i = 0; i < members.size(); i++) {
            get(i).printAllAttributes();
        }
    }

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

    public String getName() {
        return race;
    }

    public int size() {
        return members.size();
    }

    public Entity get(int i) {
        return members.get(i);
    }

    public void remove(Entity e) {
        members.remove(e);
    }

    @Override
    public ArrayList<Entity> getMembers() {
        return members;
    }
    
    public void setMembers(ArrayList<Entity> members) {
    	this.members = members;
    }

    @Override
    public void resetTeamMoved() {
        for(Entity e: members) {
            e.unsetMoved();
        }
    }

    @Override
    public void setTeamMoved() {
        for(Entity e: members) {
            e.setMoved();
        }
    }

    @Override
    public boolean isTeamsTurnFinished() {
        // Change turn if any piece in the team is moved
        for(Entity e: members) {
            if(e.isMoved()) return true;
        }
        return false;
    }

    public Entity getEntityByXY(int x, int y) {
        for(Entity e: members) {
            if(e.getXPos() == x && e.getYPos() == y) {
                return e;
            }
        }
        return null;
    }

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
