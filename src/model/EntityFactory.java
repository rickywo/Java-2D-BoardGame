package model;
import java.util.ArrayList;
import model.ProfessionNames;

public class EntityFactory {

	private ArrayList<Entity> humanTeam;
	private ArrayList<Entity> alienTeam;
	
	private static final int NUM_PIECES_PER_TEAM = 10;
	
	public EntityFactory(){
		humanTeam = new ArrayList<Entity>();
		alienTeam = new ArrayList<Entity>();
	}
	
	public void initialisePieces(){
		//assuming 10 pieces per team
		initialiseHumanTeam();
		initialiseAlienTeam();
	}
	
	private void initialiseHumanTeam(){
		//Create leader
		Entity leader = new Entity("Human1", 0);
		leader.setProfession(new Profession(ProfessionNames.COMMANDER, leader));
		humanTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity("Human"+(i+2), 0);
			entity.setProfession(new Profession(ProfessionNames.SOLDIER, entity));
			humanTeam.add(entity);
		}
	}
	
	private void initialiseAlienTeam(){
		//Create leader
		Entity leader = null;
		leader = new Entity("Alien1", 1);
		leader.setProfession(new Profession(ProfessionNames.CHIEF, leader));
		alienTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity("Alien"+(i+2), 1);
			entity.setProfession(new Profession(ProfessionNames.SPAWN, entity));
			alienTeam.add(entity);
		}
	}
	
	public ArrayList<Entity> getHumanTeam(){
		return humanTeam;
	}
	
	public ArrayList<Entity> getAlienTeam(){
		return alienTeam;
	}
	
	public void printAllPiecesAttributes(){
		System.out.println("HUMAN TEAM PIECES:");
		for(int i=0; i<humanTeam.size(); i++){
			humanTeam.get(i).printAllAttributes();
		}
		System.out.println();
		System.out.println("ALIEN TEAM PIECES:");
		for(int i=0; i<alienTeam.size(); i++){
			alienTeam.get(i).printAllAttributes();
		}
	}

	public void resetAllMoved(int team) {
		switch(team) {
			case 0: // Human team
				for(Entity e: humanTeam) {
					e.unsetMoved();
				}
				break;
			case 1: // Alien team
				for(Entity e: alienTeam) {
					e.unsetMoved();
				}
				break;
			default:
				// Do nothing
				break;
		}
	}

	public boolean isTeamTurnFinished(int team) {
		switch(team) {
			case 0: // Human team
				for(Entity e: humanTeam) {
					if(!e.isMoved()) {
						return false; // If any piece has not finished moving
					}
				}
				break;
			case 1: // Alien team
				for(Entity e: alienTeam) {
					if(!e.isMoved()) {
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
