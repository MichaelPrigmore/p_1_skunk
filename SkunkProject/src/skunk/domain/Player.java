package skunk.domain;

import java.util.function.Supplier;

public class Player
{
	private int score;
	private int chips;
	private String name;

	public Player()
	{
		this.score = 0;
		this.chips = 50;
		this.name = "default";
	}

	public Player(String name)
	{
		this.score = 0;
		this.chips = 50;
		this.name = name;
	}

	public String get_Name()
	{
		return name;
	}
}
