package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Die_Tests
{

	@Test
	public void test_die_testable_constructor()
	{
		Die myDie = new Die();
		assertNotNull(myDie);
	}

	@Test
	public void test_override_toString_die_testable()
	{
		Die myDie = new Die();
		assertEquals(myDie.toString().charAt(0), 'D');
	}

	@Test
	public void test_500_fair_roll_values()
	{
		Die myDie = new Die();

		for (int i = 0; i < 500; i++)
		{
			myDie.roll();
			assertTrue(myDie.getLastRoll() > 0 && myDie.getLastRoll() < 7);
		}
	}

	@Test
	public void test_unfair_roll_value() throws InvalidDieValueException
	{
		Die myDie = new Die();
		myDie.setIsFairDie(false);

		myDie.setLastRoll(1);
		myDie.roll();
		assertEquals(myDie.getLastRoll(), 2);

		myDie.setLastRoll(6);
		myDie.roll();
		assertEquals(myDie.getLastRoll(), 1);

	}

	@Test
	public void test_getIsFairDie() throws InvalidDieValueException
	{
		Die myDie = new Die();
		myDie.setIsFairDie(false);

		assertFalse(myDie.getIsFairDie());

	}

	@Test
	public void test_out_of_bounds_die_set()
	{
		Die myDie = new Die();

		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(0));
		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(-5));
		assertThrows(InvalidDieValueException.class, () -> myDie.setLastRoll(8));

	}

}
