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

	// @Test
	// public void test_override_toString()
	// {
	// Die myDie = new Die();
	// assertEquals(myDie.toString().charAt(0), 'D');
	// }

}
