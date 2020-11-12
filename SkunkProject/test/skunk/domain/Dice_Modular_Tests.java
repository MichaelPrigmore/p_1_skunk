package skunk.domain;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Dice_Modular_Tests
{

	@Test
	public void test_dice_modular_constructor()
	{
		Dice_Modular myDice = new Dice_Modular();
		assertNotNull(myDice);
	}

	@Test
	public void test_override_toString_dice_modular()
	{
		Dice_Modular myDice = new Dice_Modular();
		assertEquals(myDice.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_fair_dice_rolls()
	{
		Dice_Modular myDice = new Dice_Modular();

		for (int i = 0; i < 500; i++)
		{
			myDice.roll();
			assertTrue(myDice.getLastRoll() > 0 && myDice.getLastRoll() < 13);
		}
	}

	@Test
	public void unfair_dice_1_and_1_values_then_roll()
	{
		Dice_Modular myDice = new Dice_Modular();
		myDice.setIsFairDice(false);
		myDice.setLastRoll(1, 1);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 4);

	}

}
