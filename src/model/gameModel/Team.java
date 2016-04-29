package model.gameModel;

import model.gameModel.jobs.Follower;
import model.gameModel.jobs.Leader;
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
    }

    @Override
    public void initialise() {

        EntityFlyweightFactory fwFactory = GameBoard.fwFactory;
        Leader leader = null;
        Follower follower = null;

        switch (type) {
            case Human:
                race = Consts.HUMAN;
                leader = (Leader) fwFactory.createEntity(ProfessionNames.COMMANDER);
                leader.setName(race + "1");
                follower = (Follower) fwFactory.createEntity(ProfessionNames.SOLDIER);
                follower.setName(race + "2");
                break;
            case Alien:
                race = Consts.ALIEN;
                leader = (Leader) fwFactory.createEntity(ProfessionNames.CHIEF);
                leader.setName(race + "1");
                follower = (Follower) fwFactory.createEntity(ProfessionNames.SPAWN);
                follower.setName(race + "2");
                break;
            default:
                break;
        }
        //Create rest of team
        //Create soldier prototype
        members.add(leader);
        members.add(follower);
        for(int i=3; i<NUM_PIECES_PER_TEAM+1; i++){
            Entity followerCopy = (Entity)follower.clone();
            followerCopy.setName(race+ i);
            members.add(followerCopy);
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
