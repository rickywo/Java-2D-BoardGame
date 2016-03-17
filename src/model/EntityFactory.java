package model;
import java.util.ArrayList;
import model.Profession.Professions;

public class EntityFactory {
	
	private ArrayList<Entity> humanTeam = new ArrayList<Entity>();
	private ArrayList<Entity> alienTeam = new ArrayList<Entity>();
	
	private static final int NUM_PIECES_PER_TEAM = 10;
	
	public void initialisePieces(){
		//assuming 10 pieces per team
		initialiseHumanTeam();
		initialiseAlienTeam();
	}
	
	private void initialiseHumanTeam(){
		//Create leader
		Entity leader = new Entity();
		leader.setmProf(new Profession(Professions.COMMANDER));
		humanTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity();
			entity.setmProf(new Profession(Professions.SOLDIER));
			humanTeam.add(entity);
		}
	}
	
	private void initialiseAlienTeam(){
		//Create leader
		Entity leader = new Entity();
		leader.setmProf(new Profession(Professions.CHIEF));
		alienTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity();
			entity.setmProf(new Profession(Professions.SPAWN));
			alienTeam.add(entity);
		}
	}
	
	public ArrayList<Entity> getHumanTeam(){
		return humanTeam;
	}
	
	public ArrayList<Entity> getAlienTeam(){
		return alienTeam;
	}
	
}
