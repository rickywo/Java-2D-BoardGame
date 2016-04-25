package model.gameModel;
import java.util.*;

public interface EntityFactoryInterface {

	void initialisePieces();
	
	void initialiseHumanTeam();
	
	void initialiseAlienTeam();
	
	ArrayList<Entity> getHumanTeam();
	
	ArrayList<Entity> getAlienTeam();
	
	void resetTeamMoved(int team);
	
	void setTeamMoved(int team);
	
	boolean isTeamsTurnFinished(int team);
}
