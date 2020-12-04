package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myskunk.dl.Player;
import myskunk.dl.Turn;

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

	@Test
	void test_turn_current_score()
	{
		Turn turn = new Turn();

		assertEquals(0, turn.get_Current_Turn_Score());
	}

}
