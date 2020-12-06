package myskunk.pl;

import java.io.IOException;

import myskunk.dl.SkunkController;

public class SkunkApp
{

	public static void main(String[] args) throws IOException
	{
		SkunkController controller = new SkunkController();
		controller.playGame();

	}

}
