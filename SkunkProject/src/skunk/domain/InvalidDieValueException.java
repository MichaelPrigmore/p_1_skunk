package skunk.domain;

public class InvalidDieValueException extends Exception
{
	public InvalidDieValueException()
	{
		super("Invalid die value. Valid die values are 1 through 6.");
	}

}
