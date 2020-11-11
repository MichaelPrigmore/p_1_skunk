package skunk.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDie
{

	@Test
	public void test_die_constructor()
	{
		Die myDie = new Die();
		assertNotNull(myDie);
	}

	@Test
	public void test_override_toString()
	{
		fail();
	}

}
