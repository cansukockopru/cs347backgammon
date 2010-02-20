package cs347.backgammon.core.game.moves;

/**
 * List of all possible move errors. 
 */
public enum MoveErrorCondition
{
	AllCheckersInHomeArea,
	CheckersOnBarFirst,
	StartPointOwnership,
	DiceMismatch,
	EndPointOwnership,
	MoveDirection,
	MoveDistance,
	NoError;
	
/*	private String err;
	
	private MoveErrorCondition(String err)
	{
		this.err = err;
	}
	
	public String getErrorDescription()
	{
		return err;
	}*/
}
