package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testDice
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
		assertEquals(myDice.toString().charAt(0), 'd');
	}

}
