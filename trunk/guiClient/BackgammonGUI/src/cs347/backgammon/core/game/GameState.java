package cs347.backgammon.core.game;

import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.core.game.players.PlayerInfo;

/**
 * Represents and maintains the current state of the game.
 */
public class GameState
{
	private BoardState board;
	private DiceState dice;
	private PlayerInfo player1, player2;
	
	public GameState()
	{
		board = new BoardState();
		dice = new DiceState();
		
		
		player1 = new PlayerInfo();
		player2 = new PlayerInfo();
		player1.setPlayerID(PlayerID.Player1);
		player2.setPlayerID(PlayerID.Player2);
	}
	
	/**
	 * Retrieve the current board state for the game.
	 * @return The current board state.
	 */
	public BoardState getBoardState()
	{
		return board;
	}
	
	/**
	 * Retrieve the current dice state for the game.
	 * @return The current dice state.
	 */
	public DiceState getDiceState()
	{
		return dice;
	}
	
	/**
	 * Get the player information for the specified player.
	 * @param playerID Retrieve the information for this player.
	 * @return The PlayerInfo for the specified player.
	 */
	public PlayerInfo getPlayerInfo(PlayerID playerID)
	{
		if(playerID == PlayerID.Player1)
			return player1;
		else
			return player2;
	}
}
