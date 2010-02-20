package cs347.backgammon.core.game.moves;

/**
 * Immutable object containing the results from validating a move in the
 * MoveValidator.
 * 
 * @see cs347.backgammon.core.game.moves.MoveValidator#isValidMove(cs347.backgammon.core.game.GameState,
 *      Move)
 */
public class MoveValidationResult
{
	private boolean isValid;
	private MoveErrorCondition error;

	/**
	 * Initialize this result with the given parameters.
	 * 
	 * @param isValid
	 *            Is the move valid?
	 * @param error
	 *            The error condition associated with the move.
	 */
	public MoveValidationResult(boolean isValid, MoveErrorCondition error)
	{
		this.isValid = isValid;
		this.error = error;
	}

	/**
	 * Is the move valid?
	 * 
	 * @return True if the move is valid, false otherwise.
	 */
	public boolean isValid()
	{
		return isValid;
	}

	/**
	 * Did the move cause any error conditions?
	 * 
	 * @return Any error conditions caused by the invalid move.
	 */
	public MoveErrorCondition getErrorCondition()
	{
		return error;
	}

}
