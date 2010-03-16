package cs347.backgammon.core.app;

import javax.swing.SwingUtilities;

import cs347.backgammon.core.game.DiceState;
import cs347.backgammon.core.game.GameMngr;
import cs347.backgammon.core.game.Move;
import cs347.backgammon.core.game.board.CellOwner;
import cs347.backgammon.core.game.players.PlayerID;

public class BackgammonGUI
{
	private GameMngr gameMngr;

	public BackgammonGUI()
	{
		
	}

	public void setOperatorID(PlayerID operatorID)
	{
		gameMngr.setPlayerID(operatorID);
	}
	
	public void launchGameGUI()
	{
		gameMngr = new GameMngr();
		gameMngr.launchGUI();
	}
	
	public void serverUpdate(int cellID, int numCheckers, CellOwner owner)
	{
		gameMngr.getModel().serverUpdate(cellID, numCheckers, owner);
	}
	
	public void setDice(PlayerID playerID, DiceState ds)
	{
		gameMngr.setDice(playerID, ds);
	}
	
	public void setEnableOperatorInput(boolean enable)
	{
		gameMngr.setEnableOperatorInput(enable);
	}
	
	public Move getPlayerMove()
	{
		Move move = null;
		try
		{
			move = gameMngr.getMoveQueue().take();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return move;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		final BackgammonGUI app = new BackgammonGUI();

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				app.launchGameGUI();
			}
		});
	}

}
