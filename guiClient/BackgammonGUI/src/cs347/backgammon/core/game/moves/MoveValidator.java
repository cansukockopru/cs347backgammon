package cs347.backgammon.core.game.moves;

import java.awt.Point;
import java.util.EnumSet;

import cs347.backgammon.core.game.GameState;
import cs347.backgammon.core.game.board.BoardCell;
import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.board.CellOwner;
import cs347.backgammon.core.game.players.PlayerID;

/**
 * TODO
 */
public class MoveValidator
{
	private GameState gameState;
	private Move move;
	private EnumSet<MoveErrorCondition> errors;
	private MoveValidationResult result;

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
		result = null;
		this.gameState = gameState;
		this.move = move;
		errors = EnumSet.noneOf(MoveErrorCondition.class);

		MoveErrorCondition err = MoveErrorCondition.NoError;
		movingPieceOwnership();

		if (result == null)
			endPointOwnership();
		if (result == null)
			movingPieceOwnership();
		if (result == null)
			movingDirection();
		if (result == null)
			fromBarFirst();
		
		//CHeck distance
		//Check scoring?

		if (result == null)
			result = new MoveValidationResult(true, MoveErrorCondition.NoError);

		return result;
	}

	private void checkResult()
	{
		if (errors.size() > 0)
			result = new MoveValidationResult(false, errors.iterator().next());
	}

	/**
	 * Check if the starting move position is owned by the current player.
	 */
	private void movingPieceOwnership()
	{
		CellOwner fromOwner = gameState.getBoardState().getBoardCell(move.getFromID()).getCellOwner();
		PlayerID curPlayer = gameState.getCurrentPlayer();

		if (curPlayer == PlayerID.Player1 && fromOwner != CellOwner.Player1)
			errors.add(MoveErrorCondition.StartPointOwnership);
		else if (curPlayer == PlayerID.Player2 && fromOwner != CellOwner.Player2)
			errors.add(MoveErrorCondition.StartPointOwnership);
		checkResult();
	}

	/**
	 * Check if the end location of the move is empty, owned by the current
	 * player, or if the opposing player only has a single checker on the end
	 * point.
	 */
	private void endPointOwnership()
	{
		BoardCell endPoint = gameState.getBoardState().getBoardCell(move.getToID());

		if (endPoint.getCellOwner() != CellOwner.Empty)
		{
			if (endPoint.getCellOwner() == CellOwner.Player1 && gameState.getCurrentPlayer() != PlayerID.Player1)
			{
				if (endPoint.getCheckerCount() > 1)
					errors.add(MoveErrorCondition.EndPointOwnership);
			}
			else if (endPoint.getCellOwner() == CellOwner.Player2 && gameState.getCurrentPlayer() != PlayerID.Player2)
			{
				if (endPoint.getCheckerCount() > 1)
					errors.add(MoveErrorCondition.EndPointOwnership);
			}
		}
		checkResult();
	}

	/**
	 * Check if the move is in the correct direction. Checkers cannot go in
	 * reverse.
	 */
	private void movingDirection()
	{
		int pointDelta = move.getFromID() - move.getToID();

		if (gameState.getCurrentPlayer() == PlayerID.Player1)
		{
			if ((pointDelta > 0) && (move.getFromID() != BoardState.PLAYER1_BAR_ID))
				errors.add(MoveErrorCondition.MoveDirection);
		}
		else if (pointDelta < 0)
			errors.add(MoveErrorCondition.MoveDirection);
		checkResult();
	}

	/**
	 * Checkers must be moved off the bar before any other move is allowed. 
	 */
	private void fromBarFirst()
	{
		BoardCell activeBar = gameState.getBoardState().getPlayerBarCell(gameState.getCurrentPlayer());
		int barID = -1;
		if (gameState.getCurrentPlayer() == PlayerID.Player1)
			barID = BoardState.PLAYER1_BAR_ID;
		else
			barID = BoardState.PLAYER2_BAR_ID;

		if (activeBar.getCheckerCount() > 0 && move.getFromID() != barID)
			errors.add(MoveErrorCondition.CheckersOnBarFirst);
		checkResult();
	}

	private void distance(int dice)
	{
		int scoreID = -1;
		if(gameState.getCurrentPlayer() == PlayerID.Player1)
			scoreID = BoardState.PLAYER1_SCORE_ID;
		else
			scoreID = BoardState.PLAYER2_SCORE_ID;
		
		if (move.getToID() == scoreID)
		{
			if(gameState.getCurrentPlayer() == PlayerID.Player1)
			{
				if ((BoardState.NUM_STANDARD_CELLS - 1) - move.getFromID() > dice)
					errors.add(MoveErrorCondition.MoveDistance);
			}
			else if (move.getFromID() > dice)
				errors.add(MoveErrorCondition.MoveDistance);
		}
		else
		{
			int distance = Math.abs(move.getFromID() - move.getToID());
			if (distance != dice)
				errors.add(MoveErrorCondition.MoveDistance);
		}
		checkResult();
	}

	private void canScore()
	{
		if (gameState.getCurrentPlayer() == PlayerID.Player1)
		{
			int i = 0;
			for (; i < 18; i++)
			{
				if (gameState.getBoardState().getBoardCell(i).getCellOwner() == CellOwner.Player1)
				{
					errors.add(MoveErrorCondition.AllCheckersInHomeArea);
					break;
				}
			}
			/*if (i == 18)
				move.setIsScore(true);*/
		}
		else
		{
			int i = 23;
			for (; i > 5; i--)
			{
				if (gameState.getBoardState().getBoardCell(i).getCellOwner() == CellOwner.Player2)
				{
					errors.add(MoveErrorCondition.AllCheckersInHomeArea);
					break;
				}
			}
			/*if (i == 5)
				move.setIsScore(true);*/
		}
	}
}
