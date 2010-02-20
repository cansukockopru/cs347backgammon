package cs347.backgammon.core.app;

import javax.swing.SwingUtilities;

import cs347.backgammon.core.game.GameMngr;

public class BackgammonGUI
{
	private GameMngr gameMngr;

	public BackgammonGUI()
	{
		
	}

	private void launchStartupGUI()
	{

	}

	public void launchGameGUI()
	{
		gameMngr = new GameMngr();
		gameMngr.launchGUI();
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
