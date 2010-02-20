package cs347.backgammon.core.game;

import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.core.game.players.PlayerType;
import cs347.backgammon.gui.game.GameCtrl;
import cs347.backgammon.gui.game.GameView;

public class GameMngr
{
	private GameModel model;
	private GameCtrl ctrl;
	private GameView view;
	
	public GameMngr()
	{
		model = new GameModel();
		//TODO Load player info
		
		//TEMPORARY CODE FOR TESTING THE GUI
		model.getPlayerInfo(PlayerID.Player1).setPlayerType(PlayerType.GUI);
		model.getPlayerInfo(PlayerID.Player2).setPlayerType(PlayerType.GUI);
		
		
		ctrl = new GameCtrl(model);
		view = new GameView();
		
		view.setController(ctrl);
		ctrl.setView(view);
		
		model.getCurrentGameState().getBoardState().forceBoardCellUpdates();
	}
	
	public void launchGUI()
	{
		view.setVisible(true);
	}
}
