package skunk.domain;

import edu.princeton.cs.introcs.StdOut;

public class Die_Modular
{
	private int lastRoll;
	private boolean isFairDie;

	public Die_Modular()
	{
		this.roll();
		this.isFairDie = true;
	}

	public int getLastRoll() // getter or accessor method
	{

		return this.lastRoll;
	}

	public void setLastRoll(int lastRoll) throws InvalidDieValueException// setter
																			// method
	{

		if (lastRoll < 1)
		{
			InvalidDieValueException e = new InvalidDieValueException();
			StdOut.println(e.getMessage());
			throw e;
		}
		else
		{
			this.lastRoll = lastRoll;
		}

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
			if (this.lastRoll != 6)
			{
				this.lastRoll++;
			}
			else
			{
				this.lastRoll = 1;
			}
		}
	}

	@Override
	public String toString() // this OVERRIDES the default Object.toString()
	{
		return "Die: " + this.getLastRoll();
	}

	public void setIsFairDie(boolean isFairDie)
	{
		this.isFairDie = isFairDie;

	}

	public boolean getIsFairDie()
	{
		return this.isFairDie;

	}
}
