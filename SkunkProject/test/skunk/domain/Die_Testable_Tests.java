package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Die_Testable_Tests
{

	@Test
	public void test_die_testable_constructor()
	{
		Die_Testable myDie = new Die_Testable();
		assertNotNull(myDie);
	}

	@Test
	public void test_override_toString_die_testable()
	{
		Die_Testable myDie = new Die_Testable();
		assertEquals(myDie.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_fair_roll_values()
	{
		Die_Testable myDie = new Die_Testable();

		for (int i = 0; i < 500; i++)
		{
			myDie.roll();
			assertTrue(myDie.getLastRoll() > 0 && myDie.getLastRoll() < 7);
		}
	}

	@Test
	public void test_unfair_roll_value()
	{
		Die_Testable myDie = new Die_Testable();
		myDie.setIsFairDie(false);
		myDie.setLastRoll(1);
		myDie.roll();
		assertEquals(myDie.getLastRoll(), 2);

	}

}
