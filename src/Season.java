
public class Season {
	int rank;
	int score;
	int matchnumber;
	Match [] matches;
	int seasonid;
	Season(int season)
	{
		seasonid=season;
		matchnumber=38;
		matches=new Match[matchnumber];
	}
	
	Match [] getMatches()
	{
		return matches;
	}
	
}
