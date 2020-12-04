package myskunk.dl;

public class InvalidDieValueException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDieValueException()
	{
		super("Invalid die value. Valid die values are 1 through 6.");
	}

}
