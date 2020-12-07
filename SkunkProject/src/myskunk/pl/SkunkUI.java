package myskunk.pl;

import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import myskunk.dl.Player;
import myskunk.dl.SkunkController;
import myskunk.dl.SkunkController.ControllerState;
import myskunk.dl.SkunkController.RollState;
import myskunk.pl.SkunkUI;

public class SkunkUI
{
	private static final int WINNINGSCOREVALUE = 100;
	SkunkController controller;
	int numberOfPlayers;

	public SkunkUI()
	{

	}

	public SkunkUI(int numberOfPlayers, SkunkController control) // Used for
																	// testing
																	// purposes
	{
		this.controller = control;
		Player[] roster;
		this.numberOfPlayers = numberOfPlayers;
		roster = new Player[this.numberOfPlayers];

		for (int i = 0; i < this.numberOfPlayers; i++)
		{

			roster[i] = new Player();

			roster[i].setName("tester" + i);
		}

		controller.getGame().setRoster(roster);

	}

	public void beginNewGame()
	{
		Player[] roster;

		StdOut.println("Hello skunk players!\n");

		StdOut.println("How many players are there?\n");

		this.numberOfPlayers = readIntFromUser();

		roster = new Player[numberOfPlayers];

		String playerName = "default";

		for (int i = 0; i < numberOfPlayers; i++)
		{
			StdOut.println("Enter the name of player " + (i + 1) + ":");

			playerName = StdIn.readString();

			roster[i] = new Player();

			roster[i].setName(playerName);
		}

		StdOut.println("Would you like to veiw the complete set of rules? (y/n)\n");

		String decision = yesOrNoChecker();

		if (decision.equalsIgnoreCase("y"))
		{
			StdOut.println("DIRECTIONS FOR PLAYING\r\n" + "\r\n"
					+ "The object of the game is to accumulate a score of 100 points or more. A score is made by rolling the dice and combining the\n"
					+ "points on the two dice. For example: A 4 and 5 would be 9 points - if the player decides to take another roll of the dice and\n"
					+ "turns up a 3 and 5 (8 points), he would then have an accumulated total of 17 points for the two rolls. The player has the\n"
					+ "privilege of continuing to shake to increase his score or of passing the dice to wait for the next series, thus preventing the\n"
					+ "possibility of rolling a Skunk and losing his score.\n" + "\n" + "PENALTIES:\n" + "\n"
					+ "A skunk in any series voids the score for that series only and draws a penalty of 1 chip placed in the \"kitty,\" and loss of\n"
					+ "dice.\n" + "\n"
					+ "A skunk and a deuce voids the score for that series only and draws a penalty of 2 chips placed in the \"kitty,\" and loss of\n"
					+ "dice.\n" + "\n"
					+ "TWO skunks void the ENTIRE accumulated score and draws a penalty of 4 chips placed in the \"kitty,\" and loss of dice. Player\n"
					+ "must again start to score from scratch.\n" + "\n"
					+ "Any number can play. Assume at least two players. The suggested number of chips to start is 50. There are sufficient chips in\n"
					+ "the box to allow 8 players to start with 50 chips by placing a par value of \"one\" on white chips, 5 for 1 on red chips and\n"
					+ "10 for 1 on the blue chips.\n" + "\n"
					+ "The first player to accumulate a total of 100 or more points can continue to score as many points over 100 as he believes is\n"
					+ "needed to win. When he decides to stop, his total score is the \"goal.\" Each succeeding player receives one more chance to\n"
					+ "better the goal and end the game.\r\n" + "\n"
					+ "The winner of each game collects all chips in \"kitty\" and in addition five chips from each losing player or 10 chips from any\n"
					+ "player without a score.\n\n"
					+ "In the event of a tie at the end of the game between one or more players, there is no winner and the house keeps the \"kitty.\"\n");
		}

		controller.getGame().setRoster(roster);

		setControllerState(ControllerState.INITIALIZE);

		controller.trigger();

		setControllerState(ControllerState.NORMALTURNPROGRESSION);

		while (controller.getControllerState() == ControllerState.NORMALTURNPROGRESSION)
		{
			readyToContinue();

			startTurn();
		}

		for (int i = 0; i < numberOfPlayers - 1; i++)
		{
			readyToContinue();

			startTurn();
		}

		finalizeKitty();

	}

	private void readyToContinue()
	{
		StdOut.println("Enter \"y\" when your ready to continue");

		String decision = StdIn.readString();
		decision.toLowerCase();

		while (!(decision.equals("y")))
		{
			StdOut.println("Input error. Must enter \"y\" to continue.");
			decision = StdIn.readString();
		}

	}

	private String yesOrNoChecker()
	{
		String decision = StdIn.readString();
		decision.toLowerCase();

		while (!(decision.equals("y") || decision.equals("n")))
		{
			StdOut.println("Input error. Input must be \"y\" or \"n\".");
			decision = StdIn.readString();
		}

		return decision;
	}

	private int readIntFromUser()
	{
		String num = StdIn.readString();

		while (!isANumber(num) || num.charAt(0) == '0')
		{
			StdOut.println("Input error. Input must be a number and the number must be positive.");
			num = StdIn.readString();
		}

		int numAsInt = Integer.parseInt(num);

		return numAsInt;
	}

	private boolean isANumber(String num)
	{
		boolean allCharsAreNumeric = true;

		for (int i = 0; i < num.length(); i++)
		{
			if (!Character.isDigit(num.charAt(i)))
			{
				allCharsAreNumeric = false;
			}
		}

		return allCharsAreNumeric;
	}

	private void finalizeKitty()
	{
		ArrayList<Player> winners = new ArrayList<Player>();
		winners.add(controller.getGame().getRoster()[0]);

		for (int i = 1; i < numberOfPlayers; i++)
		{
			if (controller.getGame().getRoster()[i].getScore() > winners.get(0).getScore())
			{
				winners.clear();
				winners.add(controller.getGame().getRoster()[i]);
			}
			else if (controller.getGame().getRoster()[i].getScore() == winners.get(0).getScore())
			{
				winners.add(controller.getGame().getRoster()[i]);
			}
		}

		if (winners.size() > 1)
		{
			StdOut.println("Tie game! The house takes the kitty. That's why you shouldn't gamble...");
		}
		else
		{
			StdOut.println(winners.get(0).getName() + " is the winner!\n\nContinue to see the final tally.\n");
			readyToContinue();
			controller.calculateFinalChips(winners.get(0));
		}

		StdOut.println("FINAL TALLY:\n");

		for (int i = 0; i < numberOfPlayers; i++)
		{
			summerizePlayerStats(controller.getGame().getRoster()[i]);
		}

	}

	private void summerizePlayerStats(Player player)
	{
		StdOut.println(player.getName() + "'s final score: " + player.getScore() + "\n" + player.getName()
				+ "'s final number of chips: " + player.getChips() + "\n");
	}

	public void startTurn()
	{

		controller.trigger();

		StdOut.println("--------------------------------------------------\n"
				+ controller.getTurn().getPlayer().getName() + "'s Turn!" + "\n");

		StdOut.println("Chips in the kitty: " + controller.getGame().getKitty() + "\n"
				+ controller.getTurn().getPlayer().getName() + "'s chips: "
				+ controller.getTurn().getPlayer().getChips() + "\n\n" + controller.getTurn().getPlayer().getName()
				+ "'s pre-turn score: " + controller.getTurn().getPlayer().getScore() + "\n"
				+ controller.getTurn().getPlayer().getName() + "'s current turn score: "
				+ controller.getTurn().get_Current_Turn_Score() + "\n\n" + controller.getTurn().getPlayer().getName()
				+ ", would you like to roll? (y/n)");

		String decision = yesOrNoChecker();

		if (decision.equalsIgnoreCase("y"))
		{

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
						readyToContinue();
						break;

					case DOUBLESKUNK:
						rolledDoubleSkunk();
						readyToContinue();
						break;

					case SKUNKDEUCE:
						rolledDeuce();
						readyToContinue();
						break;
					}

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

					if ((controller.getTurn().getPlayer().getScore() >= WINNINGSCOREVALUE)
							&& controller.getControllerState() == ControllerState.NORMALTURNPROGRESSION)
					{
						controller.setControllerState(ControllerState.ENDGAME);
						StdOut.println(controller.getTurn().getPlayer().getName() + " has " + WINNINGSCOREVALUE
								+ " or more points! All other players get one more turn!\n");
					}
				}
			}

		}
		else
		{
			totalScore();

			endOfTurn();
		}

	}

	public void rollMessage()
	{
		StdOut.println(controller.getTurn().getPlayer().getName() + " rolled a " + controller.getMyDice().getLastRoll()
				+ " composed of a " + controller.getMyDice().getDie1().getLastRoll() + " and a "
				+ controller.getMyDice().getDie2().getLastRoll());

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
		StdOut.println("\nEnd of turn\n");

	}

	public void rolledDeuce()
	{
		StdOut.println("\nSkunk-Deuce!\n");

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

		String decision = yesOrNoChecker();

		return decision;
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
