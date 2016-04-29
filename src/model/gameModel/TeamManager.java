package model.gameModel;

public class TeamManager {

    private Team teams[] = new Team[TeamTypes.values().length];


    public TeamManager() {
        int i = 0;
        for (TeamTypes type : TeamTypes.values()) {
            teams[i] = new Team(type);
            i ++;
        }
    }

    public Team getTeam(int i) {
        return teams[i];
    }

    public void printAllPiecesAttributes() {

        for (Team team: teams) {
            System.out.println(team.getName() + " TEAM PIECES:");
            for (int i = 0; i < team.size(); i++) {
                team.get(i).printAllAttributes();
            }
            System.out.println();
        }
    }

    public void resetTeamMoved(int i) {
        for (Entity e : teams[i].getMembers()) {
            e.unsetMoved();
        }
    }

    public void setTeamMoved(int i) {
        teams[i].setTeamMoved();
    }

    public boolean isTeamsTurnFinished(int i) {
        teams[i].isTeamsTurnFinished();
        return true;
    }
}
