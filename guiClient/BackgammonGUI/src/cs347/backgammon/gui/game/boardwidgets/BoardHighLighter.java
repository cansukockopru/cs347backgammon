package cs347.backgammon.gui.game.boardwidgets;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BoardHighLighter
{
	private BackgammonBoardPanel board;

	public BoardHighLighter(BackgammonBoardPanel board)
	{
		this.board = board;
		initMouseMotionListener();
	}

	private void initMouseMotionListener()
	{
		board.getRenderable().addMouseMotionListener(new MouseMotionListener()
		{

			@Override
			public void mouseDragged(MouseEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent me)
			{
				board.highlightCell(me.getPoint());
			}
		});
	}

}
