package model.gameModel;
import java.util.*;

public interface TeamInterface {

    void initialise();

    ArrayList<Entity> getMembers();

    void resetTeamMoved();

    void setTeamMoved();

    boolean isTeamsTurnFinished();
}