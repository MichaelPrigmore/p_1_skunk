package skunk.domain;

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

	public Object getPlayer()
	{
		return this.player;
	}

	public int get_Current_Turn_Score()
	{
		return this.Current_Turn_Score;
	}

}
