package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test_Turn
{

	@Test
	void test_set_players_turn()
	{
		Player player = new Player();
		Turn turn = new Turn();
		turn.setPlayer(player);

		assertEquals(player, turn.getPlayer());
	}

}
