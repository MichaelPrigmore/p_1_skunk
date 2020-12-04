package myskunk.pl;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import myskunk.dl.Dice;
import myskunk.dl.Player;
import myskunk.dl.SkunkController;
import myskunk.dl.Turn;
import myskunk.dl.SkunkController.ControllerState;
import myskunk.dl.SkunkController.RollState;
import myskunk.pl.SkunkUI;

public class SkunkUI
{
	SkunkController controller;
	int numberOfPlayers;
	Player[] roster;

	public SkunkUI()
	{

	}

	public SkunkUI(int numberOfPlayers) // Used for testing purposes
	{
		this.numberOfPlayers = numberOfPlayers;
		roster = new Player[this.numberOfPlayers];

		for (int i = 0; i < this.numberOfPlayers; i++)
		{

			roster[i] = new Player();

			roster[i].setName("tester" + i);
		}

	}

	public void beginNewGame()
	{
		StdOut.println("Hello skunk players!\n");

		/*****************************************************
		 * Commented for 1 player and one turn per P1-2 requirements. Uncomment
		 * this section and delete next 2 lines for P1-3.
		 * 
		 * StdOut.println("How many players are there?\n");
		 * 
		 * this.numberOfPlayers = StdIn.readInt();
		 *****************************************************/

		StdOut.println("Simulate one turn for one player\n");
		this.numberOfPlayers = 1;

		roster = new Player[numberOfPlayers];

		String playerName = "default";

		for (int i = 0; i < numberOfPlayers; i++)
		{
			StdOut.println("Enter the name of player " + (i + 1) + ":");

			playerName = StdIn.readString();

			roster[i] = new Player();

			roster[i].setName(playerName);
		}

		setControllerState(ControllerState.INITIALIZE);

		controller.trigger();

		startTurn();

	}

	public void startTurn()
	{

		setControllerState(ControllerState.STARTTURN);

		controller.trigger();

		StdOut.println("\n" + controller.getTurn().getPlayer().getName() + "'s Turn!" + "\n");

		StdOut.println("Chips in the kitty: " + controller.getGame().getKitty() + "\n"
				+ controller.getTurn().getPlayer().getName() + "'s chips: "
				+ controller.getTurn().getPlayer().getChips() + "\n\n" + controller.getTurn().getPlayer().getName()
				+ "'s pre-turn score: " + controller.getTurn().getPlayer().getScore() + "\n"
				+ controller.getTurn().getPlayer().getName() + "'s current turn score: "
				+ controller.getTurn().get_Current_Turn_Score() + "\n\n" + controller.getTurn().getPlayer().getName()
				+ ", would you like to roll? (y/n)");

		String decision = StdIn.readString();

		while (decision.equalsIgnoreCase("y"))
		{

			controller.getMyDice().roll();

			rollMessage();

			controller.scoreCalculator();

			if (controller.getRollState() != RollState.NOPENALTY)
			{
				switch (controller.getRollState())
				{
				case SINGLESKUNK:
					rolledSingleSkunk();
					break;

				case DOUBLESKUNK:
					rolledDoubleSkunk();
					break;

				case SKUNKDEUCE:
					rolledDeuce();
					break;
				}

				turnSummary();

				totalScore();

				endOfTurn();

				break;

			}

			// No penalties

			else

			{

				turnSummary();

				decision = rollAgainChoice();

				if (decision.equalsIgnoreCase("n"))
				{
					controller.endTurnNoPenalty();

					totalScore();

					endOfTurn();
				}
			}

		}

	}

	public void rollMessage()
	{
		StdOut.println(controller.getTurn().getPlayer().getName() + " rolled a " + controller.getMyDice().getLastRoll()
				+ " composed of a " + controller.getMyDice().getDie1().getLastRoll() + " and a "
				+ controller.getMyDice().getDie2().getLastRoll());

	}

	public void rolledDeuce()
	{
		StdOut.println("\nSkunk-Deuce!\n");

	}

	public void turnSummary()
	{
		StdOut.println(controller.getTurn().getPlayer().getName() + "'s current turn score: "
				+ controller.getTurn().get_Current_Turn_Score() + "\n");

	}

	public void totalScore()
	{
		StdOut.println(controller.getTurn().getPlayer().getName() + "'s total score is "
				+ controller.getTurn().getPlayer().getScore() + "\n" + controller.getTurn().getPlayer().getName()
				+ "'s chips: " + controller.getTurn().getPlayer().getChips());

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

	public String rollAgainChoice()
	{
		StdOut.println(controller.getTurn().getPlayer().getName() + ", would you like to roll? (y/n)");

		String decision = StdIn.readString();

		return decision;
	}

	public Player[] getRoster()
	{
		return this.roster;
	}

	public void setController(SkunkController controller)
	{
		this.controller = controller;

	}

	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

	public void setControllerState(ControllerState controllerState)
	{
		controller.setControllerState(controllerState);
	}

}
