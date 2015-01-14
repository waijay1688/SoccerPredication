import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;


public class Train {
	static Association association;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		DataReader datareader=new DataReader("soccer");
		association=datareader.readAssociation();
		/*
		Set<String> namelist=Association.getTeamNameList().keySet();
		for(String teamname:namelist)
			System.out.println(teamname);
			*/
		Match match=getMatch("Liverpool","Everton",2010);
		if(match!=null)
			System.out.println(match.getGoal()+" "+match.getAdGoal()+" "+match.getScore());
	}
	
	static Match getMatch(String home,String away,int year)
	{
		int homeID=0;
		if(Association.getTeamNameList().containsKey(home))
		{
			homeID=Association.getTeamNameList().get(home);
		}
		else
		{
			System.out.println("No such home team name in the list");
			return null;
		}
		int awayID=0;
		if(Association.getTeamNameList().containsKey(away))
		{
			awayID=Association.getTeamNameList().get(away);
		}
		else
		{
			System.out.println("No such away team name in the list");
			return null;
		}
		
		HashMap<Integer,Team> teams=association.getTeams();
		Team homeTeam=null;
		if(teams.containsKey(homeID))
		{
			homeTeam=teams.get(homeID);
		}
		else
		{
			System.out.println("No information for this home team");
			return null; 
		}
		
		HashMap<Integer, Season> seasons=homeTeam.getSeasons();
		Season homeSeason=null;
		if(seasons.containsKey(year))
		{
			homeSeason=seasons.get(year);
		}
		else
		{
			System.out.println("No information for this season year");
			return null; 
		}
		
		Match matches[]=homeSeason.getMatches();
		int matchnumber=matches.length;
		if(matchnumber!=38)
			System.out.println("Wrong Match number for this team");
		for(int index=0;index<38;index++)
		{
			Match match=matches[index];
			if(match.getAdID()==awayID)
				return match;
		}
		System.out.println("No such away team for this home team");
		return null;
	}

}
