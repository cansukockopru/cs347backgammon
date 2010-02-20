package cs347.backgammon.core.game;

import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.board.CellOwner;

/**
 * Represents and maintains the current state of the game and informers
 * listeners of any change.
 */
public class GameModel
{
	private GameState gameState;

	public GameModel()
	{
		initGameBoard();
	}

	/**
	 * Setup the starting checker positions.
	 */
	private void initGameBoard()
	{
		BoardState board = gameState.getBoardState();
		// Player 1 setup
		board.getBoardCell(0).setCellOwner(CellOwner.Player1);
		board.getBoardCell(0).setCheckerCount(2);

		board.getBoardCell(11).setCellOwner(CellOwner.Player1);
		board.getBoardCell(11).setCheckerCount(5);

		board.getBoardCell(16).setCellOwner(CellOwner.Player1);
		board.getBoardCell(16).setCheckerCount(3);

		board.getBoardCell(18).setCellOwner(CellOwner.Player1);
		board.getBoardCell(18).setCheckerCount(5);

		// Player 2 setup
		board.getBoardCell(5).setCellOwner(CellOwner.Player2);
		board.getBoardCell(5).setCheckerCount(5);

		board.getBoardCell(7).setCellOwner(CellOwner.Player2);
		board.getBoardCell(7).setCheckerCount(3);

		board.getBoardCell(12).setCellOwner(CellOwner.Player2);
		board.getBoardCell(12).setCheckerCount(5);

		board.getBoardCell(23).setCellOwner(CellOwner.Player2);
		board.getBoardCell(23).setCheckerCount(2);
	}
}
