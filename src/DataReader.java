
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
	private Scanner _scanner;
	
	public DataReader(String filename) throws FileNotFoundException {
		this._scanner = new Scanner(new BufferedInputStream(new FileInputStream(filename)));		
	}
	
	public void close() {
		this._scanner.close();
	}
	
	public Association readAssociation() {
		Association association=new Association();
		while (this._scanner.hasNextLine()) {
			String line = this._scanner.nextLine();
			if (line.trim().length() == 0)
				   continue;
			
			String[] split_line = line.split("\t");
			if(split_line.length!=2)
				System.out.println(line+ "Association Split Problem");
			String teamname=null;
			//find a new team;
			if(split_line[0].startsWith("Team"))
			{
				teamname=split_line[1].trim();
			}
			int teamid=0;
			if(Association.getTeamNameList().containsKey(teamname))
			{
				teamid=Association.getTeamNameList().get(teamname);
			}
			else
			{
				teamid=Association.getCurrentTeamId()+1;
				Association.setCurrentTeamID(teamid);
				Association.getTeamNameList().put(teamname, teamid);
			}
			Team team=readTeam(teamid);
			association.getTeams().put(teamid, team);	
		}
		return association;
	
	}

	private Team readTeam(int teamid) {
		// TODO Auto-generated method stub
		Team team=new Team(teamid);
		while (this._scanner.hasNextLine()) {
			String line = this._scanner.nextLine();
			if (line.trim().length() == 0)
				   continue;
			
			String[] split_line = line.split("\t");
			if(split_line.length!=2)
			{
				System.out.println("Should be like Year + number");
				System.out.println(line);
						
			}
			int seasonid=0;
			if(split_line[0].startsWith("Year"))
			{				
				seasonid=Integer.valueOf(split_line[1].trim());
			}
			if(split_line[0].startsWith("Team"))
			{
				if(split_line[1].equals("END")||split_line[1].equals("End"))
					break;
				else
				{
					System.out.println("should be like Team END");
					System.out.println(line);
				}
			}
					
			Season season=readSeason(seasonid,teamid);
			
			team.getSeasons().put(seasonid, season);
		}
		return team;
	}

	private Season readSeason(int seasonid,int teamid) {
		// TODO Auto-generated method stub
		Season season=new Season(seasonid);
		int matchid=0;
		while (this._scanner.hasNextLine()) {
			String line = this._scanner.nextLine();
			if (line.trim().length() == 0)
				   continue;
			
			String[] split_line = line.split("\t");
			if(split_line[0].startsWith("Year"))
			{
				if(split_line[1].equals("END")||split_line[1].equals("End"))
					break;
				else
				{
					System.out.println("should be like Year END");
					System.out.println(line);
				}
			}
			else
			if(split_line.length!=5)
			{
				System.out.println("sholud be like the information of a team");
				System.out.println(line);
			}
			Match match=readMatch(split_line,teamid);
			if(match!=null)
			{
				season.getMatches()[matchid]=match;	
				matchid++;
			}
		}
		return season;
	
}

	private Match readMatch(String[] split_line,int teamid) {
		// TODO Auto-generated method stub
		Match match=new Match(teamid);
		int length=split_line.length;
		if(!split_line[length-1].startsWith("Premier"))
			return null;
		String teamname[]=split_line[1].split(" v ");
		String hometeam=teamname[0].trim();
		String awayteam=teamname[1].trim();
		int awayid=0;
		if(Association.getTeamNameList().containsKey(awayteam))
			awayid=Association.getTeamNameList().get(awayteam);
		else
		{
			awayid=Association.getCurrentTeamId()+1;
			Association.setCurrentTeamID(awayid);
			Association.getTeamNameList().put(awayteam, awayid);
		}
		
		int homeid=0;
		if(Association.getTeamNameList().containsKey(hometeam))
			homeid=Association.getTeamNameList().get(hometeam);
		else
		{
			homeid=Association.getCurrentTeamId()+1;
			Association.setCurrentTeamID(homeid);
			Association.getTeamNameList().put(hometeam, homeid);
		}
		
		if(homeid==teamid)
		{
			match.setHome(true);
			match.setAdId(awayid);
		}
		else
		{
			match.setHome(false);
			match.setAdId(homeid);
		}
		
		String result=split_line[2];
		if(result.contains("W"))
			match.setResult(3);
		else
		if(result.contains("D"))
			match.setResult(1);
		else
			match.setResult(0);
		
		String scoreInf=split_line[3];
		String scores[]=scoreInf.split("-");
		int homescore=Integer.valueOf(scores[0].trim());
		int awayscore=Integer.valueOf(scores[1].trim());
		if(homeid==teamid)
		{
			match.setAdGoal(awayscore);
			match.setGoal(homescore);
		}
		else
		{
			match.setAdGoal(homescore);
			match.setGoal(awayscore);
		}
		return match;
	}
}