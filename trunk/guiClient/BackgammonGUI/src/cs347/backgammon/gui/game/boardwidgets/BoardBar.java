package cs347.backgammon.gui.game.boardwidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

import cs347.backgammon.core.game.board.BoardCell;
import cs347.backgammon.core.game.board.IBoardCellListener;
import cs347.backgammon.core.game.players.PlayerID;

public class BoardBar
{
	private Color barColor;
	private RenderableBoardBar renderable;
	private int minWidth, minHeight;
	
	private IBoardCellListener player1Listener, player2Listener;
	
	public BoardBar(int minWidth, int minHeight)
	{
		barColor = Color.DARK_GRAY;
		this.minHeight = minHeight;
		this.minWidth = minWidth;
		buildGUI();
	}
	
	private void initListeners()
	{
		player1Listener = new IBoardCellListener()
		{

			@Override
			public void boardCellChanged(BoardCell boardCell)
			{
				//TODO Player 1 bar listener
			}
		};
		
		player2Listener = new IBoardCellListener()
		{

			@Override
			public void boardCellChanged(BoardCell boardCell)
			{
				//TODO Player 2 bar listener
			}
		};
	}
	
	public IBoardCellListener getBarCellListener(PlayerID playerID)
	{
		if(playerID == PlayerID.Player1)
			return player1Listener;
		else
			return player2Listener;
	}
	
	private void buildGUI()
	{
		renderable = new RenderableBoardBar();
	}
	
	public JPanel getRenderable()
	{
		return renderable;
	}
	
	//Should never be serialized
	@SuppressWarnings("serial")
	private class RenderableBoardBar extends JPanel
	{
		private int width, height;
		//private
		
		public RenderableBoardBar()
		{
			Dimension sz = new Dimension(minWidth, minHeight);
			this.setMinimumSize(sz);
			this.setPreferredSize(sz);

			this.setBackground(barColor);
			
			reSize();

			this.addComponentListener(new ComponentListener()
			{
				public void componentHidden(ComponentEvent arg0)
				{
				}

				public void componentShown(ComponentEvent arg0)
				{
				}

				public void componentMoved(ComponentEvent arg0)
				{
				}

				public void componentResized(ComponentEvent arg0)
				{
					reSize();
				}
			});
		}

		private void reSize()
		{
			width = getWidth();
			height = getHeight();
		}
	}
}
