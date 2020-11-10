package skunk.domain;

import edu.princeton.cs.introcs.StdOut;

public class SkunkApp
{

	public static void main(String[] args)
	{
		StdOut.println("Hello skunk players!");

		Dice myDice = new Dice();
		myDice.roll();

		StdOut.println(myDice.getLastRoll());

	}

}
