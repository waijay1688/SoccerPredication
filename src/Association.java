import java.util.HashMap;


public class Association {
	HashMap<Integer,Team> teams;
	static HashMap<String,Integer> teamnamelist;
	static int currentteamid;
	Association()
	{
		teams=new HashMap<Integer,Team>();
		teamnamelist=new HashMap<String,Integer>();
	}
	
	static HashMap<String,Integer> getTeamNameList()
	{
		return teamnamelist;
	}
	static int getCurrentTeamId()
	{
		return currentteamid;
	}
	static void setCurrentTeamID(int id)
	{
		currentteamid=id;
	}
	
	HashMap<Integer,Team> getTeams()
	{
		return teams;
	}
}
