package cs347.backgammon.gui.game.boardwidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.GeneralPath;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import cs347.backgammon.core.game.board.BoardCell;
import cs347.backgammon.core.game.board.CellOwner;
import cs347.backgammon.core.game.board.IBoardCellListener;
import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.gui.game.GameGUICfg;
import cs347.backgammon.gui.game.boardwidgets.checkers.CheckerGroup;

public class BoardCellWidget
{
	private int boardPointID;
	private Color triangleColor;

	private CheckerGroup checkers;
	private JPanel renderable;

	private boolean isTopRow;

	private IBoardCellListener cellListener;

	public BoardCellWidget(int id)
	{
		boardPointID = id;
		if (id < 12)
			isTopRow = false;
		else
			isTopRow = true;

		if (id % 2 == 0)
			triangleColor = GameGUICfg.getInstance().getEvenBoardCellColor();
		else
			triangleColor = GameGUICfg.getInstance().getOddBoardCellColor();;

		checkers = new CheckerGroup();
		buildGUI();
		initBoardCellListener();
	}

	private void initBoardCellListener()
	{
		cellListener = new IBoardCellListener()
		{

			@Override
			public void boardCellChanged(BoardCell boardCell)
			{
				checkers.setCheckerCount(boardCell.getCheckerCount());
				if (boardCell.getCellOwner() == CellOwner.Player1)
				{
					Color checkerColor = GameGUICfg.getInstance().getPlayerCheckerColor(PlayerID.Player1);
					checkers.setCheckerColor(checkerColor);
				}
				else if (boardCell.getCellOwner() == CellOwner.Player2)
				{
					Color checkerColor = GameGUICfg.getInstance().getPlayerCheckerColor(PlayerID.Player2);
					checkers.setCheckerColor(checkerColor);
				}
			}
		};
	}

	private void buildGUI()
	{
		renderable = new JPanel();
		renderable.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		// gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel cellIDLbl = new JLabel(Integer.toString(boardPointID));
		// cellIDLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		int minHeight = GameGUICfg.getInstance().getBoardCellMinHeight();
		int minWidth = GameGUICfg.getInstance().getBoardCellMinWidth();
		
		Dimension sz = new Dimension(minWidth, minHeight);
		JLayeredPane layered = new JLayeredPane();
		// layered.setLayout(new FlowLayout());
		layered.setMinimumSize(sz);
		layered.setPreferredSize(sz);

		// checkers.getRenderable().setMinimumSize(sz);
		// checkers.getRenderable().setPreferredSize(sz);

		RenderableBoardCell rbt = new RenderableBoardCell();
		rbt.setBounds(0, 0, minWidth, minHeight);
		checkers.getRenderable().setBounds(0, 0, minWidth, minHeight);

		layered.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		layered.add(new RenderableBoardCell(), JLayeredPane.DEFAULT_LAYER);
		layered.add(checkers.getRenderable(), JLayeredPane.PALETTE_LAYER);

		if (isTopRow)
		{
			renderable.add(cellIDLbl, gbc);
			gbc.gridy++;
			renderable.add(layered, gbc);
		}
		else
		{
			renderable.add(layered, gbc);
			gbc.gridy++;
			renderable.add(cellIDLbl, gbc);
		}
	}

	public JPanel getRenderable()
	{
		return renderable;
	}

	public IBoardCellListener getBoardCellListener()
	{
		return cellListener;
	}

	@SuppressWarnings("serial")
	// Should never be serialized
	private class RenderableBoardCell extends JPanel
	{
		private int width;
		private int height;
		private float pointHeight;

		public RenderableBoardCell()
		{
			super();
			this.setBackground(GameGUICfg.getInstance().getBoardCellBackgroundColor());
			Dimension sz = new Dimension(GameGUICfg.getInstance().getBoardCellMinWidth(), GameGUICfg.getInstance().getBoardCellMinHeight());
			this.setMinimumSize(sz);
			this.setPreferredSize(sz);
			this.setSize(sz);

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
			// checkers.getRenderable().setBounds(x, y, width, height)

			pointHeight = height * GameGUICfg.getInstance().getBoardCellTriangleTipPercent();
		}

		@Override
		public void paint(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2d.setColor(triangleColor);

			GeneralPath path = new GeneralPath();
			float halfPointWidth = width / 2;

			if (isTopRow)
			{
				path.moveTo(0, 0);
				path.lineTo(halfPointWidth, pointHeight);
				path.lineTo(width, 0);
				path.closePath();
			}
			else
			{
				path.moveTo(0, height);
				path.lineTo(halfPointWidth, height - pointHeight);
				path.lineTo(width, height);
				path.closePath();
			}

			g2d.fill(path);
		}
	}
}
