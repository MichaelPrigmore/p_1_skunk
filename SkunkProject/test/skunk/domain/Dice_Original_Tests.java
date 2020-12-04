package skunk.domain;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Extra.Dice_Original;
import Extra.Die_Original;
import myskunk.dl.Dice;

class Dice_Original_Tests
{

	@Test
	public void test_dice_first_constructor()
	{
		Dice_Original myDice = new Dice_Original();
		assertNotNull(myDice);
	}

	@Test
	public void test_dice_modular_second_constructor()
	{
		Die_Original die1 = new Die_Original();
		Die_Original die2 = new Die_Original();
		Dice_Original myDice = new Dice_Original(die1, die2);
		assertNotNull(myDice);
	}

	@Test
	public void test_override_toString_dice()
	{
		Dice_Original myDice = new Dice_Original();
		assertEquals(myDice.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_dice_rolls()
	{
		Dice_Original myDice = new Dice_Original();

		for (int i = 0; i < 500; i++)
		{
			myDice.roll();

			int die1Roll = myDice.getDie1().getLastRoll();
			int die2Roll = myDice.getDie2().getLastRoll();

			assertTrue(die1Roll > 0 && die1Roll < 7);
			assertTrue(die2Roll > 0 && die2Roll < 7);

		}
	}

}
