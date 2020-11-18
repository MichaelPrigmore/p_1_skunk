package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Die_Modular_Tests
{

	@Test
	public void test_die_testable_constructor()
	{
		Die_Modular myDie = new Die_Modular();
		assertNotNull(myDie);
	}

	@Test
	public void test_override_toString_die_testable()
	{
		Die_Modular myDie = new Die_Modular();
		assertEquals(myDie.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_fair_roll_values()
	{
		Die_Modular myDie = new Die_Modular();

		for (int i = 0; i < 500; i++)
		{
			myDie.roll();
			assertTrue(myDie.getLastRoll() > 0 && myDie.getLastRoll() < 7);
		}
	}

	@Test
	public void test_unfair_roll_value() throws InvalidDieValueException
	{
		Die_Modular myDie = new Die_Modular();
		myDie.setIsFairDie(false);
		myDie.setLastRoll(1);
		myDie.roll();
		assertEquals(myDie.getLastRoll(), 2);

	}

	@Test
	public void test_out_of_bounds_die_set()
	{
		Die_Modular myDie = new Die_Modular();

		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(0));
		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(-5));
		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(8));

	}

}
