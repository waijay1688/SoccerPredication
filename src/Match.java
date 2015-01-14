
public class Match {
	boolean home;
	int id;
	int adid;
	int goal;
	int adgoal;
	int result;
	void setHome(boolean home)
	{
		this.home=home;
	}
	
	void setAdId(int adid)
	{
		this.adid=adid;
	}
	void setGoal(int goal)
	{
		this.goal=goal;
	}
	void setAdGoal(int adgoal)
	{
		this.adgoal=adgoal;
	}
	
	void setResult(int result)
	{
		this.result=result;
	}
	
	Match(int teamid)
	{
		id=teamid;
	}
	
	int getAdID()
	{
		return this.adid;
	}
	
	int getGoal()
	{
		return this.goal;
	}
	
	int getAdGoal()
	{
		return this.adgoal;
	}
	
	int getScore()
	{
		return this.result;
	}
	
}
