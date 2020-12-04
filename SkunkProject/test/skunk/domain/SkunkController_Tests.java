package skunk.domain;

import static org.junit.Assert.assertEquals;
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

	}

	// @Test
	// void test_playGame() throws InvalidDieValueException
	// {
	// SkunkController controller = new SkunkController(false);
	// controller.getMyDice().setLastRoll(2, 2);
	// controller.
	// controller.playGame();
	// // Enter "y" and then "n" in console after run
	// assertEquals(controller.getTurn().getPlayer().getScore(), 6);
	//
	// }

	// @Test
	// void test_scoreCalculator_Skunk_Deuce() throws InvalidDieValueException
	// {
	// SkunkController controller = new SkunkController(false);
	// Turn turn = new Turn();
	// Dice myDice = new Dice();
	// myDice.setIsFairDice(false);
	// myDice.setLastRoll(2, 1);
	// controller.scoreCalculator(turn, myDice);
	//
	// assertEquals(controller.getRollState(), RollState.SKUNKDEUCE);
	// }
	//
	// @Test
	// void test_scoreCalculator_Single_Skunk() throws InvalidDieValueException
	// {
	// SkunkController controller = new SkunkController();
	// Turn turn = new Turn();
	// Dice myDice = new Dice();
	// myDice.setIsFairDice(false);
	// myDice.setLastRoll(3, 1);
	// controller.scoreCalculator(turn, myDice);
	//
	// assertEquals(controller.getRollState(), RollState.SINGLESKUNK);
	// }
	//
	// @Test
	// void test_scoreCalculator_Double_Skunk() throws InvalidDieValueException
	// {
	// SkunkController controller = new SkunkController();
	// Turn turn = new Turn();
	// Dice myDice = new Dice();
	// myDice.setIsFairDice(false);
	// myDice.setLastRoll(1, 1);
	// controller.scoreCalculator(turn, myDice);
	//
	// assertEquals(controller.getRollState(), RollState.DOUBLESKUNK);
	// }

}
