package Extra;

public class Die_Original
{
	private int lastRoll;

	public Die_Original()
	{
		this.roll();
	}

	public int getLastRoll() // getter or accessor method
	{

		return this.lastRoll;
	}

	public void roll() // note how this changes Die's state, but doesn't return
						// anything
	{
		this.lastRoll = (int) (Math.random() * 6 + 1);
	}

	@Override
	public String toString() // this OVERRIDES the default Object.toString()
	{
		return "Die: " + this.getLastRoll();
	}

}
