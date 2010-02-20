package cs347.backgammon.core.game.moves;

import cs347.backgammon.core.game.GameState;

/**
 * TODO
 */
public class MoveGenerator
{

	public MoveGenerator()
	{

	}

	/**
	 * Generate all possible moves for the given GameState.
	 * @param gameState The GameState to base move generation from.
	 * @return All possible moves for the given GameState.
	 */
	public MoveSet generateAllMoves(GameState gameState)
	{
		MoveSet ms = new MoveSet(gameState);
		//TODO Implement move generation.
		return ms;
	}
}
