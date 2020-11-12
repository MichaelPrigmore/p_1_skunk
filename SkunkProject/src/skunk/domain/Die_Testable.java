package skunk.domain;

public class Die_Testable
{
	private int lastRoll;
	private boolean isFairDie;

	public Die_Testable()
	{
		this.roll();
		this.isFairDie = true;
	}

	public int getLastRoll() // getter or accessor method
	{

		return this.lastRoll;
	}

	public void setLastRoll(int lastRoll) // setter method
	{

		this.lastRoll = lastRoll;
	}

	public void roll() // note how this changes Die's state, but doesn't return
						// anything
	{
		if (isFairDie == true)
		{
			this.lastRoll = (int) (Math.random() * 6 + 1);
		}
		else
		{

		}
	}

	@Override
	public String toString() // this OVERRIDES the default Object.toString()
	{
		return "Die: " + this.getLastRoll();
	}
}
