package cs347.backgammon.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs347.backgammon.core.game.players.PlayerID;
import cs347.backgammon.core.game.players.PlayerType;

public class PlayerPanel
{
	private JPanel panel;
	private JLabel name, score, timeRemaining, type;
	private SixSidedDice dice1, dice2;
	private JButton rollDice;
	
	public PlayerPanel(PlayerID playerID)
	{
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(playerID.toString()));
		panel.setMinimumSize(new Dimension(150, 180));
		panel.setPreferredSize(new Dimension(150, 180));
		
		
		
		name = new JLabel();
		score = new JLabel();
		timeRemaining = new JLabel();
		type = new JLabel();
		
		dice1 = new SixSidedDice();
		dice2 = new SixSidedDice();
		
		rollDice = new JButton("Roll Dice");
		
		buildGUI();
	}

	private void buildGUI()
	{
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		panel.add(new JLabel("Name:"), gbc);
		gbc.gridx++;
		panel.add(name, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		
		panel.add(new JLabel("Type:"), gbc);
		gbc.gridx++;
		panel.add(type, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		
		panel.add(new JLabel("Score:"), gbc);
		gbc.gridx++;
		panel.add(score, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		
		panel.add(new JLabel("Time Remaining:"), gbc);
		gbc.gridx++;
		panel.add(timeRemaining, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(dice1.getRenderable(), gbc);
		gbc.gridx++;
		panel.add(dice2.getRenderable(), gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		
		gbc.gridwidth = 2;
		panel.add(rollDice, gbc);
	}

	public void setIsOperator()
	{
		panel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
	}
	
	
	public void setName(String name)
	{
		this.name.setText(name);
	}
	
	public void setScore(int score)
	{
		String scoreAsString = Integer.toString(score);
		this.score.setText(scoreAsString);
	}
	
	public void setPlayerType(PlayerType type)
	{
		this.type.setText(type.toString());
	}
	
	public void setTimeRemaining(long seconds)
	{
		//Convert seconds to minutes and seconds
		long numMinutes = seconds / 60L;
		int numSeconds = ((int)seconds) % 60;
		timeRemaining.setText(numMinutes + ":" + numSeconds);
	}
	
	public void setDice1(byte dieValue)
	{
		dice1.setValue(dieValue);
	}
	
	public void setDice2(byte dieValue)
	{
		dice2.setValue(dieValue);
	}
	
	public JPanel getRenderable()
	{
		return panel;
	}
}
