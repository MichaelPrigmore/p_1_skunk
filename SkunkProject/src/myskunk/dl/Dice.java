package myskunk.dl;

public class Dice
{
	// Instance fields (variables) may be declared anywhere in class body
	// Convention: put at top

	private int lastRoll;
	private Die die1;
	private Die die2;

	// Constructors (object initializers) also can be declared anywhere
	// Convention: after instance fields/variables

	public Dice()
	{
		// initialize instance variables die1 and die2 by
		// creating a new instance of each

		this.die1 = new Die();
		this.die2 = new Die();
		this.roll();
	}

	public Dice(Die die1, Die die2) // overloaded
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

	public Die getDie1()
	{
		return this.die1;
	}

	public Die getDie2()
	{
		return this.die2;
	}

	public void setLastRoll(int die1Roll, int die2Roll) throws InvalidDieValueException
	{
		this.die1.setLastRoll(die1Roll);
		this.die2.setLastRoll(die2Roll);
		this.lastRoll = die1Roll + die2Roll;

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

}
