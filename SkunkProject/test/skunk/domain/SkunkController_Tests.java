package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import skunk.dl.Dice;
import skunk.dl.InvalidDieValueException;
import skunk.dl.Player;
import skunk.dl.SkunkController;
import skunk.dl.SkunkController.ControllerState;
import skunk.dl.SkunkController.RollState;
import skunk.dl.Turn;

class SkunkController_Tests
{

	@Test
	public void test_SkunkController_first_constructor()
	{
		SkunkController controller = new SkunkController();
		assertNotNull(controller);
	}

	@Test
	void test_trigger_initialize() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(true, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();
		assertEquals(controller.getWhosTurn(), 0);

	}

	@Test
	void test_trigger_startturn() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(true, 3);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();
		assertEquals(controller.getWhosTurn(), 0);

		controller.trigger();
		assertEquals(controller.getWhosTurn(), 1);

		controller.trigger();
		assertEquals(controller.getWhosTurn(), 2);

		controller.trigger();
		assertEquals(controller.getWhosTurn(), 0);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_state() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_state_with_reversed_dice() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(2, 1);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_kitty_player_had_50_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
		assertEquals(controller.getTurn().getPlayer().getChips(), 48);
		assertEquals(controller.getGame().getKitty(), 2);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_kitty_player_had_30_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.getTurn().getPlayer().setChips(30);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
		assertEquals(controller.getTurn().getPlayer().getChips(), 28);
		assertEquals(controller.getGame().getKitty(), 2);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_kitty_player_had_2_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.getTurn().getPlayer().setChips(2);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getGame().getKitty(), 2);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_kitty_player_had_1_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.getTurn().getPlayer().setChips(1);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_skunk_deuce_kitty_player_had_0_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 2);

		controller.getTurn().getPlayer().setChips(0);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getGame().getKitty(), 0);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_state() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_kitty_player_had_50_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 46);
		assertEquals(controller.getTurn().getPlayer().getScore(), 0);
		assertEquals(controller.getGame().getKitty(), 4);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_kitty_player_had_30_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);

		controller.getTurn().getPlayer().setChips(30);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 26);
		assertEquals(controller.getTurn().getPlayer().getScore(), 0);
		assertEquals(controller.getGame().getKitty(), 4);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_kitty_player_had_4_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);

		controller.getTurn().getPlayer().setChips(4);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getTurn().getPlayer().getScore(), 0);
		assertEquals(controller.getGame().getKitty(), 4);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_kitty_player_had_1_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);

		controller.getTurn().getPlayer().setChips(1);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getTurn().getPlayer().getScore(), 0);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_double_skunk_kitty_player_had_0_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 1);

		controller.getTurn().getPlayer().setChips(0);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getTurn().getPlayer().getScore(), 0);
		assertEquals(controller.getGame().getKitty(), 0);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_state() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 3);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_state_with_reversed_dice() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(3, 1);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_kitty_player_had_50_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 3);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 49);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_kitty_player_had_30_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 3);

		controller.getTurn().getPlayer().setChips(30);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 29);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_kitty_player_had_2_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 4);

		controller.getTurn().getPlayer().setChips(2);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 1);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_kitty_player_had_1_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 5);

		controller.getTurn().getPlayer().setChips(1);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getGame().getKitty(), 1);

	}

	@Test
	void test_trigger_scoreCalculator_single_skunk_kitty_player_had_0_chips() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(1, 6);

		controller.getTurn().getPlayer().setChips(0);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
		assertEquals(controller.getTurn().getPlayer().getChips(), 0);
		assertEquals(controller.getGame().getKitty(), 0);

	}

	@Test
	void test_trigger_scoreCalculator_no_penalty() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(4, 3);

		controller.scoreCalculator();

		assertEquals(controller.getRollState(), RollState.NOPENALTY);
		assertEquals(controller.getTurn().getPlayer().getChips(), 50);
		assertEquals(controller.getTurn().get_Current_Turn_Score(), 7);

	}

	@Test
	void test_trigger_scoreCalculator_end_turn_no_penalty() throws InvalidDieValueException
	{
		SkunkController controller = new SkunkController(false, 1);
		controller.setControllerState(ControllerState.INITIALIZE);
		controller.trigger();

		controller.setControllerState(ControllerState.STARTTURN);
		controller.trigger();

		controller.getMyDice().setLastRoll(2, 6);
		controller.getTurn().getPlayer().setScore(25);

		controller.scoreCalculator();
		controller.endTurnNoPenalty();

		assertEquals(controller.getRollState(), RollState.NOPENALTY);
		assertEquals(controller.getTurn().getPlayer().getChips(), 50);
		assertEquals(controller.getTurn().getPlayer().getScore(), 33);

	}

}
