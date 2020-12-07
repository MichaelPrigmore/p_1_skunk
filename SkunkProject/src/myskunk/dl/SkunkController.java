package myskunk.dl;

import myskunk.pl.SkunkUI;

public class SkunkController
{
	private int SINGLE_SKUNK_CHIP_PENALTY = 1;
	private int SKUNK_DUECE_CHIP_PENALTY = 2;
	private int DOUBLE_SKUNK_CHIP_PENALTY = 4;

	private int tempChips;
	private Integer tempKitty;

	private Dice myDice = new Dice();
	private Turn turn = new Turn();
	private SkunkUI UI;
	private Game game;
	public RollState rollState;
	public ControllerState controllerState;
	private Integer whosTurn;

	public enum RollState
	{
		NOPENALTY, SINGLESKUNK, DOUBLESKUNK, SKUNKDEUCE
	}

	public enum ControllerState
	{
		INITIALIZE, NORMALTURNPROGRESSION, ENDGAME
	}

	public SkunkController()
	{
		game = new Game();

		UI = new SkunkUI();

		UI.setController(this);

	}

	public SkunkController(Boolean cheatModeOff, int numberOfTestPlayers) // Used
																			// for
																			// testing
																			// purposes
	{
		game = new Game();

		UI = new SkunkUI(numberOfTestPlayers);

		UI.setController(this);

		this.myDice.setIsFairDice(cheatModeOff);

	}

	public void playGame()
	{
		UI.beginNewGame();
	}

	public void trigger()
	{
		switch (controllerState)
		{
		case INITIALIZE:
			initializeNewGame();
			break;
		case NORMALTURNPROGRESSION:
			incramentAndSetWhosTurn();
			break;
		case ENDGAME:
			incramentAndSetWhosTurn();
			break;
		}

	}

	private void initializeNewGame()
	{

		game.setRoster(UI.getRoster());
		whosTurn = UI.getNumberOfPlayers() - 1;
	}

	private void incramentAndSetWhosTurn()
	{
		if (whosTurn == UI.getNumberOfPlayers() - 1)
		{
			whosTurn = 0;
		}
		else
		{
			whosTurn++;
		}

		turn.setPlayer(game.getRoster()[whosTurn]);

	}

	public void endTurnNoPenalty()
	{
		int score = turn.getPlayer().getScore() + turn.get_Current_Turn_Score();
		turn.getPlayer().setScore(score);

	}

	public void scoreCalculator()
	{

		// Skunk-Deuce Penalty

		if ((myDice.getDie1().getLastRoll() == 1 && myDice.getDie2().getLastRoll() == 2)
				|| (myDice.getDie1().getLastRoll() == 2 && myDice.getDie2().getLastRoll() == 1))
		{

			if ((turn.getPlayer().getChips() - SKUNK_DUECE_CHIP_PENALTY) >= 0)
			{
				tempChips = turn.getPlayer().getChips() - SKUNK_DUECE_CHIP_PENALTY;

				turn.getPlayer().setChips(tempChips);

				tempKitty = game.getKitty() + SKUNK_DUECE_CHIP_PENALTY;
				game.setKitty(tempKitty);
			}
			else
			{
				tempKitty = game.getKitty() + turn.getPlayer().getChips();
				game.setKitty(tempKitty);
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
				tempChips = turn.getPlayer().getChips() - SINGLE_SKUNK_CHIP_PENALTY;

				turn.getPlayer().setChips(tempChips);

				tempKitty = game.getKitty() + SINGLE_SKUNK_CHIP_PENALTY;
				game.setKitty(tempKitty);

			}

			turn.set_Current_Turn_Score(0);
			rollState = RollState.SINGLESKUNK;

		}

		// Double Skunk Penalty

		else if ((myDice.getDie1().getLastRoll() == 1 && myDice.getDie2().getLastRoll() == 1))
		{

			if ((turn.getPlayer().getChips() - DOUBLE_SKUNK_CHIP_PENALTY) >= 0)
			{
				tempChips = turn.getPlayer().getChips() - DOUBLE_SKUNK_CHIP_PENALTY;

				turn.getPlayer().setChips(tempChips);

				tempKitty = game.getKitty() + DOUBLE_SKUNK_CHIP_PENALTY;
				game.setKitty(tempKitty);

			}
			else
			{
				tempKitty = game.getKitty() + turn.getPlayer().getChips();
				game.setKitty(tempKitty);
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

	public void calculateFinalChips(Player winningPlayer)
	{
		for (int i = 0; i < game.getRoster().length; i++)
		{
			if (!(game.getRoster()[i].equals(winningPlayer)))
			{
				if (game.getRoster()[i].getScore() > 0)
				{
					if (game.getRoster()[i].getChips() >= 5)
					{
						game.getRoster()[i].setChips(game.getRoster()[i].getChips() - 5);
						game.setKitty(game.getKitty() + 5);
					}
					else
					{
						game.setKitty(game.getKitty() + game.getRoster()[i].getChips());
						game.getRoster()[i].setChips(0);
					}
				}
				else
				{
					if (game.getRoster()[i].getChips() >= 10)
					{
						game.getRoster()[i].setChips(game.getRoster()[i].getChips() - 10);
						game.setKitty(game.getKitty() + 10);
					}
					else
					{
						game.setKitty(game.getKitty() + game.getRoster()[i].getChips());
						game.getRoster()[i].setChips(0);
					}
				}
			}
		}

		winningPlayer.setChips(winningPlayer.getChips() + game.getKitty());
		game.setKitty(0);

	}

	public void setControllerState(ControllerState controllerState)
	{
		this.controllerState = controllerState;

	}

	public ControllerState getControllerState()
	{
		return controllerState;
	}

	public Game getGame()
	{
		return game;
	}

	public Integer getWhosTurn()
	{
		return whosTurn;
	}

	public Dice getMyDice()
	{
		return myDice;
	}

	public Turn getTurn()
	{
		return turn;
	}

	public RollState getRollState()
	{
		return rollState;
	}

}
