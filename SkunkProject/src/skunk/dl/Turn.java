package skunk.dl;

public class Turn
{
	private Player player;
	private int Current_Turn_Score;

	public Turn()
	{
		this.Current_Turn_Score = 0;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
		this.Current_Turn_Score = 0;

	}

	public Player getPlayer()
	{
		return this.player;
	}

	public int get_Current_Turn_Score()
	{
		return this.Current_Turn_Score;
	}

	public void set_Current_Turn_Score(int Current_Turn_Score)
	{
		this.Current_Turn_Score = Current_Turn_Score;
	}

}
