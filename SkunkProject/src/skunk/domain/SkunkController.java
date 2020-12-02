package skunk.domain;

import myskunk.pl.SkunkUI;

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

		this.playerArray = new Player[1];

		Player player1 = new Player("Goku");

		Dice myDice = new Dice();

		int kitty = 0;

		Turn turn = new Turn();

		turn.setPlayer(player1);

		String decision = "";

		decision = UI.startTurn(turn, kitty);

		while (decision.equalsIgnoreCase("y"))
		{

			myDice.roll();

			UI.rollMessage(turn, myDice);

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

				UI.rolledDeuce();

				UI.turnSummary(turn, kitty);

				UI.totalScore(turn);

				UI.endOfTurn();

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

				UI.rolledSingleSkunk();

				UI.turnSummary(turn, kitty);

				UI.totalScore(turn);

				UI.endOfTurn();

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

				turn.getPlayer().setScore(0);

				UI.rolledDoubleSkunk();

				UI.turnSummary(turn, kitty);

				UI.totalScore(turn);

				UI.endOfTurn();

				break;

			}

			// No penalties

			else
			{

				turn.set_Current_Turn_Score(turn.get_Current_Turn_Score() + myDice.getLastRoll());

				UI.turnSummary(turn, kitty);

				decision = UI.rollAgainChoice(turn);

				if (decision.equalsIgnoreCase("n"))
				{
					score = turn.getPlayer().getScore() + turn.get_Current_Turn_Score();
					turn.getPlayer().setScore(score);

					UI.totalScore(turn);

					UI.endOfTurn();
				}
			}

		}
	}

}
