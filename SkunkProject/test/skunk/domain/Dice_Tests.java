package skunk.domain;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Dice_Tests
{

	@Test
	public void test_dice_modular_first_constructor()
	{
		Dice myDice = new Dice();
		assertNotNull(myDice);
	}

	@Test
	public void test_dice_modular_second_constructor()
	{
		Die die1 = new Die();
		Die die2 = new Die();
		Dice myDice = new Dice(die1, die2);
		assertNotNull(myDice);
	}

	@Test
	public void test_override_toString_dice_modular()
	{
		Dice myDice = new Dice();
		assertEquals(myDice.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_fair_dice_rolls()
	{
		Dice myDice = new Dice();

		for (int i = 0; i < 500; i++)
		{
			myDice.roll();
			assertTrue(myDice.getLastRoll() > 0 && myDice.getLastRoll() < 13);
		}
	}

	@Test
	public void roll_unfair_dice_inputs_from_1_to_6() throws InvalidDieValueException
	{
		Dice myDice = new Dice();
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

}
