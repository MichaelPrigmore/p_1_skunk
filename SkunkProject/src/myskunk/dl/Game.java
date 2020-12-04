package myskunk.dl;

import myskunk.pl.SkunkUI;

public class Game
{
	private Integer kitty;
	private Player[] roster;

	public Game()
	{
		this.kitty = 0;
	}

	public Integer getKitty()
	{
		return kitty;
	}

	public void setKitty(Integer kitty)
	{
		this.kitty = kitty;
	}

	public Player[] getRoster()
	{
		return roster;
	}

	public void setRoster(Player[] roster)
	{
		this.roster = roster;
	}
}
