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
    private boolean turnFlag;

    public Team(TeamTypes type) {
        this.type = type;
        turnFlag = true;
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

    @Override
    public ArrayList<Entity> getMembers() {
        return members;
    }

    @Override
    public void resetTeamMoved() {
        turnFlag = true;
    }

    @Override
    public void setTeamMoved() {
        turnFlag = false;
    }

    @Override
    public boolean isTeamsTurnFinished() {
        return turnFlag;
    }
}
