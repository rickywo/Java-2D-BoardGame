package model.gameModel;

import resources.Consts;
import java.util.ArrayList;
import model.gameModel.jobs.*;

public class EntityFactory implements EntityFactoryInterface {

    private ArrayList<Entity> humanTeam;
    private ArrayList<Entity> alienTeam;

    private static final int NUM_PIECES_PER_TEAM = Consts.NUM_PIECES_PER_TEAM;

    public EntityFactory() {
        humanTeam = new ArrayList<Entity>();
        alienTeam = new ArrayList<Entity>();
    }

    @Override
    public void initialisePieces() {
        //assuming 10 pieces per team
        initialiseHumanTeam();
        initialiseAlienTeam();
    }

    @Override
    public void initialiseHumanTeam() {
        //Create leader
        Entity commander = new Commander("Human1");
        humanTeam.add(commander);
        //Create rest of team
        //Create soldier prototype
        Entity soldier = new Soldier("Human2");
        humanTeam.add(soldier);
        for(int i=3; i<NUM_PIECES_PER_TEAM+1; i++){
        	Entity soldierCopy = (Entity)soldier.clone();
        	soldierCopy.setName("Human"+ i);
        	humanTeam.add(soldierCopy);
        }
    }
    
    @Override
    public void initialiseAlienTeam() {
        //Create leader
        Entity chief = new Chief("Alien1");
        alienTeam.add(chief);
        //Create rest of team
        Entity spawn = new Spawn("Alien2");
        alienTeam.add(spawn);
        for(int i=3; i<NUM_PIECES_PER_TEAM+1; i++){
        	Entity spawnCopy = (Entity)spawn.clone();
        	spawnCopy.setName("Alien"+ i);
        	alienTeam.add(spawnCopy);
        }
    }

    @Override
    public ArrayList<Entity> getHumanTeam() {
        return humanTeam;
    }

    @Override
    public ArrayList<Entity> getAlienTeam() {
        return alienTeam;
    }

    public void printAllPiecesAttributes() {
        System.out.println("HUMAN TEAM PIECES:");
        for (int i = 0; i < humanTeam.size(); i++) {
            humanTeam.get(i).printAllAttributes();
        }
        System.out.println();
        System.out.println("ALIEN TEAM PIECES:");
        for (int i = 0; i < alienTeam.size(); i++) {
            alienTeam.get(i).printAllAttributes();
        }
    }

    @Override
    public void resetTeamMoved(int team) {
        switch (team) {
            case 0: // Human team
                for (Entity e : humanTeam) {
                    e.unsetMoved();
                }
                break;
            case 1: // Alien team
                for (Entity e : alienTeam) {
                    e.unsetMoved();
                }
                break;
            default:
                // Do nothing
                break;
        }
    }

    @Override
    public void setTeamMoved(int team) {
        switch (team) {
            case 0: // Human team
                for (Entity e : humanTeam) {
                    e.setMoved();
                }
                break;
            case 1: // Alien team
                for (Entity e : alienTeam) {
                    e.setMoved();
                }
                break;
            default:
                // Do nothing
                break;
        }
    }

    @Override
    public boolean isTeamsTurnFinished(int team) {
        switch (team) {
            case 0: // Human team
                for (Entity e : humanTeam) {
                    if (!e.isMoved()) {
                        return false; // If any piece has not finished moving
                    }
                }
                break;
            case 1: // Alien team
                for (Entity e : alienTeam) {
                    if (!e.isMoved()) {
                        return false; // If any piece has not finished moving
                    }
                }
                break;
            default:
                // Do nothing
                break;
        }
        return true;
    }
}
