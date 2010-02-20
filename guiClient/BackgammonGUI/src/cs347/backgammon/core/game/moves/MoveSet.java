package cs347.backgammon.core.game.moves;

import cs347.backgammon.core.game.GameState;

/**
 * A tree of all possible moves from a given GameState.
 */
public class MoveSet
{
	private GameState gameState;

	/**
	 * 
	 * @param gameState The GameState that all moves in this set are based from.
	 */
	public MoveSet(GameState gameState)
	{
		this.gameState = gameState;
	}

	
}
