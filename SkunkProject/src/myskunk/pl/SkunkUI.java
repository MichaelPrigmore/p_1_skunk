package myskunk.pl;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import myskunk.pl.SkunkUI;
import skunk.dl.Dice;
import skunk.dl.Player;
import skunk.dl.SkunkController;
import skunk.dl.Turn;
import skunk.dl.SkunkController.ControllerState;

public class SkunkUI
{
	SkunkController controller;
	int numberOfPlayers;
	Player[] roster;

	public SkunkUI()
	{

	}

	public void setController(SkunkController controller)
	{
		this.controller = controller;

	}

	public void beginNewGame()
	{
		StdOut.println("Hello skunk players!\n");

		StdOut.println("How many players are there?\n");

		this.numberOfPlayers = StdIn.readInt();

		Player[] roster = new Player[numberOfPlayers];

		String playerName = "default";

		for (int i = 0; i < numberOfPlayers; i++)
		{
			StdOut.println("Enter the name of player " + (i + 1) + ":");

			playerName = StdIn.readString();

			roster[i].setName(playerName);
		}

		setControllerState(ControllerState.INITIALIZE);

		controller.trigger();

	}

	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

	public void setControllerState(ControllerState controllerState)
	{
		controller.setControllerState(controllerState);
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

	public Player[] getRoster()
	{
		return this.roster;
	}

}
