package skunk.dl;

import myskunk.pl.SkunkUI;

public class SkunkController
{
	private int SINGLE_SKUNK_CHIP_PENALTY = 1;
	private int SKUNK_DUECE_CHIP_PENALTY = 2;
	private int DOUBLE_SKUNK_CHIP_PENALTY = 4;
	// private int numberOfPlayers;
	// private Player[] playerArray;

	private String decision = "";
	private int chips;
	private int score;
	private int kitty = 0;
	private Dice myDice = new Dice();
	private Turn turn = new Turn();
	private SkunkUI UI = new SkunkUI();
	private RollState rollState;

	public enum RollState
	{
		NOPENALTY, SINGLESKUNK, DOUBLESKUNK, SKUNKDEUCE
	}

	public SkunkController()
	{

		// this.playerArray = new Player[1];

		Player player1 = new Player("Goku");

		turn.setPlayer(player1); // simulating just one turn for now

	}

	public SkunkController(Boolean cheatModeOff)
	{

		// this.playerArray = new Player[1];

		Player player1 = new Player("Goku");

		turn.setPlayer(player1); // simulating just one turn for now

		this.myDice.setIsFairDice(cheatModeOff);

	}

	public String getDecision()
	{
		return decision;
	}

	public void setDecision(String decision)
	{
		this.decision = decision;
	}

	public int getChips()
	{
		return chips;
	}

	public void setChips(int chips)
	{
		this.chips = chips;
	}

	public int getKitty()
	{
		return kitty;
	}

	public void setKitty(int kitty)
	{
		this.kitty = kitty;
	}

	public Dice getMyDice()
	{
		return myDice;
	}

	public void setMyDice(Dice myDice)
	{
		this.myDice = myDice;
	}

	public Turn getTurn()
	{
		return turn;
	}

	public void setTurn(Turn turn)
	{
		this.turn = turn;
	}

	public RollState getRollState()
	{
		return rollState;
	}

	public void setRollState(RollState rollState)
	{
		this.rollState = rollState;
	}

	public void playGame()
	{
		decision = UI.startTurn(turn, kitty);

		while (decision.equalsIgnoreCase("y"))
		{

			myDice.roll();

			UI.rollMessage(turn, myDice);

			scoreCalculator(turn, myDice);

			if (rollState != RollState.NOPENALTY)
			{
				switch (rollState)
				{
				case SINGLESKUNK:
					UI.rolledSingleSkunk();
					break;

				case DOUBLESKUNK:
					UI.rolledDoubleSkunk();
					break;

				case SKUNKDEUCE:
					UI.rolledDeuce();
					break;
				}

				UI.turnSummary(turn, kitty);

				UI.totalScore(turn);

				UI.endOfTurn();

				break;

			}

			// No penalties

			else

			{

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

	public void scoreCalculator(Turn turn, Dice myDice)
	{

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
			rollState = RollState.SKUNKDEUCE;

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
			rollState = RollState.SINGLESKUNK;

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
			rollState = RollState.DOUBLESKUNK;

		}

		else

		{

			turn.set_Current_Turn_Score(turn.get_Current_Turn_Score() + myDice.getLastRoll());
			rollState = RollState.NOPENALTY;
		}

	}

}
