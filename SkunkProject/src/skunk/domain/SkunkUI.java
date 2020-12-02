package skunk.domain;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkUI
{
	int numberOfPlayers;

	public SkunkUI()
	{
		StdOut.println("Hello skunk players!\n");

		StdOut.println("Simulate 1 turn for one player\n");

		// Just one player for P_1.2

		// StdOut.println("Enter number of players: \n");

		// numberOfPlayers = StdIn.readInt();

		numberOfPlayers = 1;

	}

	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers)
	{
		this.numberOfPlayers = numberOfPlayers;
	}

}
