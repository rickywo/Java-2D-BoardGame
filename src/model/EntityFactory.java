package model;
import java.util.ArrayList;
import model.Profession.Professions;

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
		Entity leader = new Entity("Commander", 0);
		leader.setProfession(new Profession(Professions.COMMANDER, leader));
		humanTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity("Soldier", 0);
			entity.setProfession(new Profession(Professions.SOLDIER, entity));
			humanTeam.add(entity);
		}
	}
	
	private void initialiseAlienTeam(){
		//Create leader
		Entity leader = null;
		leader = new Entity("Chief", 1);
		leader.setProfession(new Profession(Professions.CHIEF, leader));
		alienTeam.add(leader);
		//Create rest of team
		for(int i=0; i<NUM_PIECES_PER_TEAM-1; i++){
			Entity entity = new Entity("Spawn", 1);
			entity.setProfession(new Profession(Professions.SPAWN, entity));
			alienTeam.add(entity);
		}
	}
	
	public ArrayList<Entity> getHumanTeam(){
		//TODO remove later
		System.out.println("HUMAN TEAM PIECES:");
		for(int i=0; i<humanTeam.size(); i++){
			humanTeam.get(i).printAllAttributes();
		}
		return humanTeam;
	}
	
	public ArrayList<Entity> getAlienTeam(){
		//TODO remove later
		System.out.println("ALIEN TEAM PIECES:");
		for(int i=0; i<alienTeam.size(); i++){
			alienTeam.get(i).printAllAttributes();
		}
		return alienTeam;
	}
	
}
