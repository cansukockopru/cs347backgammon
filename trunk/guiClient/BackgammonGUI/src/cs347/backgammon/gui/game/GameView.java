package cs347.backgammon.gui.game;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs347.backgammon.core.game.DiceState;
import cs347.backgammon.core.game.board.BoardState;
import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.core.game.players.PlayerInfo;
import cs347.backgammon.gui.game.boardwidgets.BackgammonBoardPanel;
import cs347.backgammon.gui.game.boardwidgets.checkers.Checker;

public class GameView
{
	private JFrame frame;
	private PlayerPanel player1Pan, player2Pan;
	// private BackgammonBoardPanel boardPan;
	private BackgammonBoardPanel boardPan;
	
	private GameCtrl ctrl;

	public GameView()
	{
		frame = new JFrame("test");
		// TODO release resources on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player1Pan = new PlayerPanel(PlayerID.Player1);
		player2Pan = new PlayerPanel(PlayerID.Player2);

		Checker.initConfiguration(25, 25);
		boardPan = new BackgammonBoardPanel();

		//frame.setJMenuBar(new GameMenuBar().getGUIMenuBar());
		
		buildGUI();
		frame.pack();
		frame.setVisible(false);
	}
	
	public void setVisible(boolean isVisible)
	{
		frame.setVisible(isVisible);
	}

	public void init(BoardState boardState, PlayerInfo p1, PlayerInfo p2)
	{
		boardPan.init(boardState, ctrl);
		initPlayerPanel(player1Pan, p1);
		initPlayerPanel(player2Pan, p2);
	}
	
	public void setOperatorID(PlayerID operatorID)
	{
		if(operatorID == PlayerID.Player1)
			player1Pan.setIsOperator();
		else
			player2Pan.setIsOperator();
	}
	
	private void initPlayerPanel(PlayerPanel pan, PlayerInfo info)
	{
		pan.setName(info.getName());
		pan.setPlayerType(info.getPlayerType());
		
		//type
		//time remaining?
		//Dice?
	}
	
	public void setController(GameCtrl ctrl)
	{
		this.ctrl = ctrl;
	}

	public void alertOnOperatorTurn()
	{
		//TODO FIgure out a better way to inform operator.
		//Flashing gui panel?
		JOptionPane.showMessageDialog(frame, "Your turn.");
	}
	
	public void setDice(PlayerID playerID, DiceState ds)
	{
		if(playerID == PlayerID.Player1)
		{
			player1Pan.setDice1((byte)ds.getDice1Value());
			player1Pan.setDice2((byte)ds.getDice2Value());
		}
		else
		{
			player2Pan.setDice1((byte)ds.getDice1Value());
			player2Pan.setDice2((byte)ds.getDice2Value());			
		}
	}
	
	private void buildGUI()
	{
		// frame.setLayout(new BorderLayout());

		// boardPan.setBorder(BorderFactory.createEtchedBorder());
		// frame.add(buildPlayerPanels(), BorderLayout.LINE_START);
		// frame.add(boardPan.getRenderable(), BorderLayout.CENTER);

		
	/*	frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		frame.add(buildPlayerPanels(), gbc);
		gbc.gridx++;
		frame.add(boardPan.getRenderable(), gbc);*/
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.add(buildPlayerPanels());
		frame.add(boardPan.getRenderable());
	}

	private JPanel buildPlayerPanels()
	{
		JPanel pan = new JPanel();
		// pan.setLayout(new BorderLayout());
		//		
		// pan.add(player1Pan.getGUI(), BorderLayout.PAGE_START);
		// pan.add(player2Pan.getGUI(), BorderLayout.PAGE_END);

		pan.setLayout(new GridLayout(2, 1));
		pan.add(player1Pan.getRenderable());
		pan.add(player2Pan.getRenderable());

		return pan;
	}
}

