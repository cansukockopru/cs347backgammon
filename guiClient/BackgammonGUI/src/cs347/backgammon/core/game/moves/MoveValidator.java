package cs347.backgammon.core.game.moves;

import cs347.backgammon.core.game.GameState;

/**
 * TODO
 */
public class MoveValidator
{

	public MoveValidator()
	{

	}

	/**
	 * Is the given move valid for the current GameState?
	 * 
	 * @param gameState
	 *            The GameState to base move validation from.
	 * @param move
	 *            The move to validate.
	 * @return The results from the move validation.
	 */
	public MoveValidationResult isValidMove(GameState gameState, Move move)
	{
		MoveValidationResult result = new MoveValidationResult(true, MoveErrorCondition.NoError);
		//TODO Implement validation rules.
		return result;
	}
}
