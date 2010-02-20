package cs347.backgammon.gui.game.boardwidgets;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.gui.game.GameGUICfg;

public class BackgammonBoardPanel
{
	private BoardBar bar;
	private BoardCellWidget[] cells;
	private RenderableBackgammonBoard renderable;

	public BackgammonBoardPanel()
	{

		bar = new BoardBar(GameGUICfg.getInstance().getBoardBarWidth(), GameGUICfg.getInstance().getBoardBarHeight());

		cells = new BoardCellWidget[24];
		for (int i = 0; i < 24; i++)
			cells[i] = new BoardCellWidget(i);
		
		//TODO Score cells?
		
		renderable = new RenderableBackgammonBoard();
	}

	public void init(BoardState boardState)
	{
		for (int i = 0; i < 24; i++)
			boardState.getBoardCell(i).setBoardCellListener(cells[i].getBoardCellListener());

		boardState.getBoardCell(BoardState.PLAYER1_BAR_ID).setBoardCellListener(bar.getBarCellListener(PlayerID.Player1));
		boardState.getBoardCell(BoardState.PLAYER2_BAR_ID).setBoardCellListener(bar.getBarCellListener(PlayerID.Player2));
		
		//TODO Score cells?
	}
	
	public JPanel getRenderable()
	{
		return renderable;
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
