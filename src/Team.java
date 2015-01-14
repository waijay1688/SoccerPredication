import java.util.HashMap;


public class Team {
	
	HashMap<Integer,Season> seasons;
	Team(int team)
	{
		this.teamid=team;
		seasons=new HashMap<Integer,Season>();
	}
	int teamid;
	
	HashMap<Integer,Season> getSeasons()
	{
		return seasons;
	}
}
