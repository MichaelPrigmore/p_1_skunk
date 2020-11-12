package skunk.domain;

import edu.princeton.cs.introcs.StdOut;

public class Dice_Modular
{
	// Instance fields (variables) may be declared anywhere in class body
	// Convention: put at top

	private int lastRoll;
	private Die_Modular die1;
	private Die_Modular die2;

	// Constructors (object initializers) also can be declared anywhere
	// Convention: after instance fields/variables

	public Dice_Modular()
	{
		// initialize instance variables die1 and die2 by
		// creating a new instance of each

		this.die1 = new Die_Modular();
		this.die2 = new Die_Modular();
		this.roll();
	}

	public Dice_Modular(Die_Modular die1, Die_Modular die2) // overloaded
															// constructor
	{
		this.die1 = die1;
		this.die2 = die2;
	}

	public void setIsFairDice(boolean isFairDice)
	{
		this.die1.setIsFairDie(isFairDice);
		this.die2.setIsFairDie(isFairDice);

	}

	// Instance methods can also be declared anywhere in body of class
	// One convention: after the constructors

	public int getLastRoll()
	{
		return this.lastRoll;

	}

	public void setLastRoll(int die1Roll, int die2Roll)
	{
		this.die1.setLastRoll(die1Roll);
		this.die2.setLastRoll(die2Roll);

	}

	public void roll()
	{
		// Roll each of die1, die2, sum their last rolls,
		// then set Dice.lastRoll to this value

		this.die1.roll();
		this.die2.roll();
		this.lastRoll = die1.getLastRoll() + die2.getLastRoll();

	}

	// the following method converts the internals of
	// this Dice object, and returns a descriptive String:
	//
	// Roll of 7 => 4 + 3
	//

	public String toString()
	{
		return "Dice with last roll: " + getLastRoll() + " => " + die1.getLastRoll() + " + " + die2.getLastRoll();
	}

	// static methods can go anywhere - but at end is one convention

	public static final int NUM_TRIALS = 360;

	public static void main(String[] args)
	{
		// simulate repeated rolls of Dice, counting the many double skunks

		Dice_Modular dice = new Dice_Modular();
		int doubleSkunkCount = 0;

		for (int i = 0; i < NUM_TRIALS; i++)
		{
			dice.roll();
			StdOut.println(dice);

			if (dice.getLastRoll() == 2)
				doubleSkunkCount++;
		}

		StdOut.println("Actual count: " + doubleSkunkCount);
		StdOut.println("Expected count: " + (NUM_TRIALS / 36.0));
	}

}
