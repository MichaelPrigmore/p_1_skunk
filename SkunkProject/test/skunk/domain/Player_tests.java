package skunk.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Player_tests
{

	@Test
	public void test_player_first_constructor()
	{
		Player player = new Player();
		assertNotNull(player);
	}

}
