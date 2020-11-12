package skunk.domain;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class Dice_Tests
{

	@Test
	public void test_dice_constructor()
	{
		Dice myDice = new Dice();
		assertNotNull(myDice);
	}

	@Test
	public void test_override_toString_dice()
	{
		Dice myDice = new Dice();
		assertEquals(myDice.toString().charAt(0), 'D');
	}

}
