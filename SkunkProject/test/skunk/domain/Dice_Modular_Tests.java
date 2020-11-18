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
	public void roll_unfair_dice_inputs_from_1_to_6()
	{
		Dice_Modular myDice = new Dice_Modular();
		myDice.setIsFairDice(false);

		myDice.setLastRoll(1, 1);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 4);

		myDice.setLastRoll(2, 4);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 8);

		myDice.setLastRoll(3, 5);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 10);

		myDice.setLastRoll(5, 6);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 7);

		myDice.setLastRoll(6, 6);
		myDice.roll();
		assertEquals(myDice.getLastRoll(), 2);

	}

	// @Test
	// public void roll_unfair_dice_inputs_out_of_bounds()
	// {
	// Dice_Modular myDice = new Dice_Modular();
	// myDice.setIsFairDice(false);
	//
	// myDice.setLastRoll(-1, 1);
	// myDice.roll();
	// assertEquals(myDice.getLastRoll(), 4);
	//
	//
	// }

}
