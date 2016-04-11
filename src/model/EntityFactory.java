package model;

import resources.Consts;

import java.util.ArrayList;

public class EntityFactory {

    private ArrayList<Entity> humanTeam;
    private ArrayList<Entity> alienTeam;

    private static final int NUM_PIECES_PER_TEAM = 4;

    public EntityFactory() {
        humanTeam = new ArrayList<Entity>();
        alienTeam = new ArrayList<Entity>();
    }

    public void initialisePieces() {
        //assuming 10 pieces per team
        initialiseHumanTeam();
        initialiseAlienTeam();
    }

    private void initialiseHumanTeam() {
        //Create leader
        Entity leader = new Entity("Human1", 0);
        leader.setProfession(new Profession(ProfessionNames.COMMANDER, leader));
        humanTeam.add(leader);
        //Create rest of team
        for (int i = 0; i < NUM_PIECES_PER_TEAM - 1; i++) {
            Entity entity = new Entity(Consts.HUMAN + (i + 2), Consts.HUMAN_TEAM_NUM);
            entity.setProfession(new Profession(ProfessionNames.SOLDIER, entity));
            humanTeam.add(entity);
        }
    }

    private void initialiseAlienTeam() {
        //Create leader
        Entity leader = null;
        leader = new Entity("Alien1", 1);
        leader.setProfession(new Profession(ProfessionNames.CHIEF, leader));
        alienTeam.add(leader);
        //Create rest of team
        for (int i = 0; i < NUM_PIECES_PER_TEAM - 1; i++) {
            Entity entity = new Entity(Consts.ALIEN + (i + 2), Consts.ALIEN_TEAM_NUM);
            entity.setProfession(new Profession(ProfessionNames.SPAWN, entity));
            alienTeam.add(entity);
        }
    }

    public ArrayList<Entity> getHumanTeam() {
        return humanTeam;
    }

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
