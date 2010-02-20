package cs347.backgammon.gui.game;

import java.awt.Color;

import cs347.backgammon.core.game.players.PlayerID;

public class GameGUICfg
{

	private static GameGUICfg instance;
	
	private GameGUICfg()
	{
		
	}
	
	public static GameGUICfg getInstance()
	{
		if(instance == null)
			instance = new GameGUICfg();
		return instance;
	}
	
	public Color getPlayerCheckerColor(PlayerID playerID)
	{
		if(playerID == PlayerID.Player1)
			return Color.ORANGE;
		else
			return Color.GREEN;
	}
	
	public Color getEvenBoardCellColor()
	{
		return Color.BLACK;
	}
	
	public Color getOddBoardCellColor()
	{
		return Color.RED;
	}
	
	public Color getBoardCellBackgroundColor()
	{
		return Color.DARK_GRAY;
	}
	
	public int getBoardCellMinWidth()
	{
		return 75;
	}
	
	public int getBoardCellMinHeight()
	{
		return 250;
	}
	
	public float getBoardCellTriangleTipPercent()
	{
		return 0.9f;
	}
	
	public int getBoardBarWidth()
	{
		return getBoardCellMinWidth();
	}
	
	public int getBoardBarHeight()
	{
		return getBoardCellMinHeight() * 2;
	}
}
