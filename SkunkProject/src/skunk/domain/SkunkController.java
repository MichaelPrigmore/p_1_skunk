package skunk.domain;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkController
{
	int SINGLE_SKUNK_CHIP_PENALTY = 1;
	int SKUNK_DUECE_CHIP_PENALTY = 2;
	int DOUBLE_SKUNK_CHIP_PENALTY = 4;
	int numberOfPlayers;
	Player[] playerArray;

	int chips;
	int score;

	public SkunkController()
	{
		SkunkUI UI = new SkunkUI();

		Player[] playerArray = new Player[UI.numberOfPlayers];

		Player player1 = new Player("Goku");

		Dice_Original myDice = new Dice_Original();

		int kitty = 0;

		Turn turn = new Turn();
		turn.setPlayer(player1);

		String decision;

		StdOut.println(turn.getPlayer().getName() + "'s Turn!" + "\n");

		StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
				+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
				+ "'s chips: " + turn.getPlayer().getChips() + "\n" + turn.getPlayer().getName()
				+ ", would you like to roll? (y/n)");

		decision = StdIn.readString();

		while (decision.equalsIgnoreCase("y"))
		{

			myDice.roll();

			StdOut.println(turn.getPlayer().getName() + " rolled a " + myDice.getLastRoll() + " composed of a "
					+ myDice.getDie1().getLastRoll() + " and a " + myDice.getDie2().getLastRoll());

			// Conditions

			// Skunk-Deuce Penalty

			if ((myDice.getDie1().getLastRoll() == 1 && myDice.getDie2().getLastRoll() == 2)
					|| (myDice.getDie1().getLastRoll() == 2 && myDice.getDie2().getLastRoll() == 1))
			{

				if ((turn.getPlayer().getChips() - SKUNK_DUECE_CHIP_PENALTY) >= 0)
				{
					chips = turn.getPlayer().getChips() - SKUNK_DUECE_CHIP_PENALTY;

					turn.getPlayer().setChips(chips);

					kitty += SKUNK_DUECE_CHIP_PENALTY;
				}
				else
				{
					kitty += turn.getPlayer().getChips();
					turn.getPlayer().setChips(0);
				}

				turn.set_Current_Turn_Score(0);

				StdOut.println("\nSkunk-Deuce!\n");

				StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
						+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
						+ "'s chips: " + turn.getPlayer().getChips());

				StdOut.println(turn.getPlayer().getName() + "'s total score is " + turn.getPlayer().getScore());
				StdOut.println("End of turn");

				break;

			}

			// Single Skunk Penalty

			else if ((myDice.getDie1().getLastRoll() == 1 && myDice.getDie2().getLastRoll() != 1)
					|| (myDice.getDie1().getLastRoll() != 1 && myDice.getDie2().getLastRoll() == 1))
			{

				if ((turn.getPlayer().getChips() - SINGLE_SKUNK_CHIP_PENALTY) >= 0)
				{
					chips = turn.getPlayer().getChips() - SINGLE_SKUNK_CHIP_PENALTY;

					turn.getPlayer().setChips(chips);

					kitty += SINGLE_SKUNK_CHIP_PENALTY;
				}

				turn.set_Current_Turn_Score(0);

				StdOut.println("\nSkunk!\n");

				StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
						+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
						+ "'s chips: " + turn.getPlayer().getChips());

				StdOut.println(turn.getPlayer().getName() + "'s total score is " + turn.getPlayer().getScore());
				StdOut.println("End of turn");

				break;

			}

			// Double Skunk Penalty

			else if ((myDice.getDie1().getLastRoll() == 1 && myDice.getDie2().getLastRoll() == 1))
			{

				if ((turn.getPlayer().getChips() - DOUBLE_SKUNK_CHIP_PENALTY) >= 0)
				{
					chips = turn.getPlayer().getChips() - DOUBLE_SKUNK_CHIP_PENALTY;

					turn.getPlayer().setChips(chips);

					kitty += DOUBLE_SKUNK_CHIP_PENALTY;
				}
				else
				{
					kitty += turn.getPlayer().getChips();
					turn.getPlayer().setChips(0);
				}

				turn.set_Current_Turn_Score(0);

				StdOut.println("\nDouble Skunk!\n");

				StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
						+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
						+ "'s chips: " + turn.getPlayer().getChips());

				turn.getPlayer().setScore(0);

				StdOut.println(turn.getPlayer().getName() + "'s total score is " + turn.getPlayer().getScore());
				StdOut.println("End of turn");

				break;

			}

			// No penalties

			else
			{

				turn.set_Current_Turn_Score(turn.get_Current_Turn_Score() + myDice.getLastRoll());

				StdOut.println("Pre-turn score: " + turn.getPlayer().getScore() + "\n" + "Current turn score: "
						+ turn.get_Current_Turn_Score() + "\n" + "Kitty: " + kitty + "\n" + turn.getPlayer().getName()
						+ "'s chips: " + turn.getPlayer().getChips() + "\n" + turn.getPlayer().getName()
						+ ", would you like to roll? (y/n)");

				decision = StdIn.readString();

				if (decision.equalsIgnoreCase("n"))
				{
					score = turn.getPlayer().getScore() + turn.get_Current_Turn_Score();
					turn.getPlayer().setScore(score);

					StdOut.println(turn.getPlayer().getName() + "'s total score is " + turn.getPlayer().getScore());
					StdOut.println("End of turn");
				}
			}

		}
	}

}
