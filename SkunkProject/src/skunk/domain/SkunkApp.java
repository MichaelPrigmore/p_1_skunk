package skunk.domain;

import edu.princeton.cs.introcs.StdOut;

public class SkunkApp
{

	public static void main(String[] args)
	{
		StdOut.println("Hello skunk players!");

		StdOut.println("Simulate 1 turn for one player");

		Player player1 = new Player("Goku");

		Turn turn = new Turn();
		turn.setPlayer(player1);

		Dice myDice = new Dice();
		myDice.roll();

		StdOut.println(myDice.getLastRoll());

	}

}
