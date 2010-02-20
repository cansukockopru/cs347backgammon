package cs347.backgammon.gui.game;

import cs347.backgammon.core.game.GameModel;
import cs347.backgammon.core.game.players.PlayerID;

public class GameCtrl
{
	private GameView view;
	private GameModel model;

	public GameCtrl(GameModel model)
	{
		this.model = model;
	}

	public void setView(GameView gv)
	{
		this.view = gv;
		view.init(model.getCurrentGameState().getBoardState(), 
				model.getPlayerInfo(PlayerID.Player1), 
				model.getPlayerInfo(PlayerID.Player2));
	}
}
