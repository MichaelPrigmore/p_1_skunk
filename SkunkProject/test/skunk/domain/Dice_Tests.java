package skunk.domain;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class Dice_Tests
{

	@Test
	public void test_dice_first_constructor()
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
	public void test_override_toString_dice()
	{
		Dice myDice = new Dice();
		assertEquals(myDice.toString().charAt(0), 'D');
	}

}
