package skunk.domain;

import edu.princeton.cs.introcs.StdOut;

public class SkunkApp
{

	public static void main(String[] args)
	{
		StdOut.println("Hello skunk players!\n");

		StdOut.println("Simulate 1 turn for one player\n");

		Player player1 = new Player("Goku");

		Turn turn = new Turn();
		turn.setPlayer(player1);

		StdOut.println(turn.getPlayer().get);

		Dice myDice = new Dice();
		myDice.roll();

		StdOut.println(myDice.getLastRoll());

	}

}
