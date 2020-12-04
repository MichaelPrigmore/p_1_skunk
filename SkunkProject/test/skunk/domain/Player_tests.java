package skunk.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myskunk.dl.Player;

class Player_tests
{

	@Test
	public void test_player_first_constructor()
	{
		Player player = new Player();
		assertNotNull(player);
	}

	@Test
	public void test_player_second_constructor()
	{
		Player player = new Player("Michael");
		assertNotNull(player);
	}

	@Test
	public void test_get_Name()
	{
		Player player = new Player("Michael");
		assertEquals("Michael", player.getName());
	}

}
