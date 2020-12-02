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

	public String startTurn(Turn turn, int kitty)
	{
		StdOut.println(turn.getPlayer().getName() + "'s Turn!" + "\n");

		StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
				+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
				+ "'s chips: " + turn.getPlayer().getChips() + "\n" + turn.getPlayer().getName()
				+ ", would you like to roll? (y/n)");

		String decision = StdIn.readString();

		return decision;
	}

	public void rollMessage(Turn turn, Dice myDice)
	{
		StdOut.println(turn.getPlayer().getName() + " rolled a " + myDice.getLastRoll() + " composed of a "
				+ myDice.getDie1().getLastRoll() + " and a " + myDice.getDie2().getLastRoll());

	}

	public void rolledDeuce()
	{
		StdOut.println("\nSkunk-Deuce!\n");

	}

	public void turnSummary(Turn turn, int kitty)
	{
		StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
				+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
				+ "'s chips: " + turn.getPlayer().getChips());

	}

	public void totalScore(Turn turn)
	{
		StdOut.println(turn.getPlayer().getName() + "'s total score is " + turn.getPlayer().getScore());

	}

	public void endOfTurn()
	{
		StdOut.println("End of turn");

	}

	public void rolledSingleSkunk()
	{
		StdOut.println("\nSkunk!\n");

	}

	public void rolledDoubleSkunk()
	{
		StdOut.println("\nDouble Skunk!\n");

	}

	public String rollAgainChoice(Turn turn)
	{
		StdOut.println("\n" + turn.getPlayer().getName() + ", would you like to roll? (y/n)");

		String decision = StdIn.readString();

		return decision;
	}

}
