package skunk.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import Extra.Die_Original;

public class Die_Original_Tests
{

	@Test
	public void test_die_constructor()
	{
		Die_Original myDie = new Die_Original();
		assertNotNull(myDie);
	}

	@Test
	public void test_override_toString_die()
	{
		Die_Original myDie = new Die_Original();
		assertEquals(myDie.toString().charAt(0), 'D');
	}

}
