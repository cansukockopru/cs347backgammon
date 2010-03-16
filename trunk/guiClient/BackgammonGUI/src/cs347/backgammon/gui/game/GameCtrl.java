package cs347.backgammon.gui.game;

import cs347.backgammon.core.game.GameMngr;
import cs347.backgammon.core.game.GameModel;
import cs347.backgammon.core.game.Move;
import cs347.backgammon.core.game.players.PlayerID;

public class GameCtrl
{
	private GameView view;
	private GameModel model;
	private boolean isEnableOperator;
	private PlayerID operatorID;
	private GameMngr gameMngr;
	
	public GameCtrl(GameModel model, GameMngr mngr)
	{
		this.model = model;
		isEnableOperator = false;
		this.gameMngr = mngr;
	}

	public void setView(GameView gv)
	{
		this.view = gv;
		view.init(model.getCurrentGameState().getBoardState(), model
				.getPlayerInfo(PlayerID.Player1), model
				.getPlayerInfo(PlayerID.Player2));
	}

	public void setEnableOperator(boolean enable)
	{
		isEnableOperator = enable;
	}

	public boolean isEnableOperator()
	{
		return isEnableOperator;
	}

	public PlayerID getPlayerID()
	{
		return operatorID;
	}

	public void sendMove(int fromID, int toID)
	{
		// FIXME Send move data to server

		// Temporary testing
		//model.applyMove(fromID, toID);
		gameMngr.sendMove(new Move(fromID, toID));
	}
}
