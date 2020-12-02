package skunk.domain;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

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

}
