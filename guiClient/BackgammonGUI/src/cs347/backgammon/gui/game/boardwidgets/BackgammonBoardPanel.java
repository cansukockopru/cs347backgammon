package cs347.backgammon.gui.game.boardwidgets;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.gui.game.GameGUICfg;

public class BackgammonBoardPanel
{
	private BoardBar bar;
	private BoardCellWidget[] cells;
	private RenderableBackgammonBoard renderable;
	private int curHighlightCell;

	public BackgammonBoardPanel()
	{
		curHighlightCell = -1;
		bar = new BoardBar(GameGUICfg.getInstance().getBoardBarWidth(), GameGUICfg.getInstance().getBoardBarHeight());

		cells = new BoardCellWidget[24];
		for (int i = 0; i < 24; i++)
			cells[i] = new BoardCellWidget(i);

		// TODO Score cells?

		renderable = new RenderableBackgammonBoard();
	}

	public void init(BoardState boardState)
	{
		for (int i = 0; i < 24; i++)
			boardState.getBoardCell(i).setBoardCellListener(cells[i].getBoardCellListener());

		boardState.getBoardCell(BoardState.PLAYER1_BAR_ID).setBoardCellListener(
				bar.getBarCellListener(PlayerID.Player1));
		boardState.getBoardCell(BoardState.PLAYER2_BAR_ID).setBoardCellListener(
				bar.getBarCellListener(PlayerID.Player2));

		// TODO Score cells?
	}

	public JPanel getRenderable()
	{
		return renderable;
	}

	public int mouseOverCell(Point point)
	{
		int curCellWidth = cells[0].getRenderable().getWidth();
		int curCellHeight = cells[0].getRenderable().getHeight();

		//Purposefully do integer division
		int column = point.x / curCellWidth;
		int row = point.y / curCellHeight;

		System.out.println("Row: "+row+" Col: "+column);
		
		int cellID = -1;

		if (column == 6)// Bar
		{
			if (row == 0)
				cellID = BoardState.PLAYER1_BAR_ID;
			else
				cellID = BoardState.PLAYER2_BAR_ID;
		}
		else
		{
			if (row == 0) // Top row
			{
				// Top row starts at 12 on the left
				if(column > 6)
					cellID = 12 + column - 1; //Minus 1 for the bar column
				else
					cellID = 12 + column;
			}
			else// Bottom row
			{
				// Bottom row ends at 11 on the left.
				if(column > 6)
					cellID = 11 - column + 1; //Plus 1 for the bar column 	
				else
					cellID = 11 - column;
			}
		}

		return cellID;
	}

	public void highlightCell(Point mouseLocation)
	{
		if(curHighlightCell != -1)
		{
			if(curHighlightCell < cells.length)
				cells[curHighlightCell].disableHighlight();
		}
		
		curHighlightCell = mouseOverCell(mouseLocation);

		if(curHighlightCell != -1)
		{
			Border temp = BorderFactory.createLineBorder(Color.CYAN);
			if(curHighlightCell < cells.length)
				cells[curHighlightCell].setHighlightMode(BoardCellWidget.HighlightMode.Hover);
			else
			{
				switch(curHighlightCell)
				{
				case BoardState.PLAYER1_BAR_ID:
					//TODO highlight player 1 bar
					break;
				case BoardState.PLAYER2_BAR_ID:
					//TODO highlight player 2 bar
					break;
				case BoardState.PLAYER1_SCORE_ID:
					//TODO highlight player 1 score cell
					break;
				case BoardState.PLAYER2_SCORE_ID:
					//TODO highlight player 1 score cell
					break;
				}
			}
		}
	}

	@SuppressWarnings("serial")
	// Should never be serialized
	private class RenderableBackgammonBoard extends JPanel
	{
		public RenderableBackgammonBoard()
		{
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			this.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;

			gbc.gridx = 0;
			gbc.gridy = 0;

			// Top left chunk
			for (int i = 12; i < 18; i++)
			{
				this.add(cells[i].getRenderable(), gbc);
				gbc.gridx++;
			}

			// Draw bar?
			gbc.gridheight = 2;
			this.add(bar.getRenderable(), gbc);
			gbc.gridheight = 1;

			// Top right chunk
			gbc.gridx++;
			for (int i = 18; i < 24; i++)
			{
				this.add(cells[i].getRenderable(), gbc);
				gbc.gridx++;
			}

			// Bottom left chunk
			gbc.gridy++;
			gbc.gridx = 0;
			for (int i = 11; i > 5; i--)
			{
				this.add(cells[i].getRenderable(), gbc);
				gbc.gridx++;
			}

			// Skip bar location
			gbc.gridx++;

			// Bottom right chunk
			for (int i = 5; i > -1; i--)
			{
				this.add(cells[i].getRenderable(), gbc);
				gbc.gridx++;
			}
		}
	}

}
