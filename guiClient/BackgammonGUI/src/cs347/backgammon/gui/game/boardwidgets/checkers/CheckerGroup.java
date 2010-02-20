package cs347.backgammon.gui.game.boardwidgets.checkers;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CheckerGroup
{
	private static String single = "Single";
	private static String dual = "Dual";

	private CardLayout cardLayout;
	private JPanel renderable;

	private List<Checker> leftGroup, rightGroup, centerGroup;
	private int checkerCount;

	public CheckerGroup()
	{
		renderable = new JPanel();
		cardLayout = new CardLayout();

		checkerCount = 0;

		leftGroup = new ArrayList<Checker>(8);
		rightGroup = new ArrayList<Checker>(8);
		centerGroup = new ArrayList<Checker>(8);

		buildGUI();
	}

	private void buildGUI()
	{
		for (int i = 0; i < 8; i++)
		{
			leftGroup.add(new Checker());
			rightGroup.add(new Checker());
			centerGroup.add(new Checker());
		}

		renderable.setOpaque(false);
		renderable.setLayout(cardLayout);
		renderable.add(single, buildSingle());
		renderable.add(dual, buildDual());
	}

	private JPanel buildSingle()
	{
		JPanel pan = new JPanel();
		pan.setOpaque(false);

		pan.setLayout(new GridLayout(8, 1));
		for (Checker c : centerGroup)
			pan.add(c.getRenderable());
		return pan;
	}

	private JPanel buildDual()
	{
		JPanel pan = new JPanel();
		pan.setOpaque(false);

		pan.setLayout(new GridLayout(8, 2));

		for (int i = 0; i < 8; i++)
		{
			pan.add(leftGroup.get(i).getRenderable());
			pan.add(rightGroup.get(i).getRenderable());
		}
		return pan;
	}

	public void setCheckerColor(Color color)
	{
		for (int i = 0; i < 8; i++)
		{
			leftGroup.get(i).setCheckerColor(color);
			rightGroup.get(i).setCheckerColor(color);
			centerGroup.get(i).setCheckerColor(color);
		}
	}
	
	public void setCheckerCount(int checkerCount)
	{
		this.checkerCount = checkerCount;
		resetRenderState();
	}

	private void resetRenderState()
	{
		for (int i = 0; i < 8; i++)
		{
			leftGroup.get(i).getRenderable().setVisible(false);
			rightGroup.get(i).getRenderable().setVisible(false);
			centerGroup.get(i).getRenderable().setVisible(false);
		}

		if (checkerCount < 9)
		{
			for (int i = 0; i < checkerCount; i++)
				centerGroup.get(i).getRenderable().setVisible(true);
			cardLayout.show(renderable, single);
		}
		else
		{
			for (int i = 0; i < 8; i++)
				leftGroup.get(i).getRenderable().setVisible(true);
			int remainder = checkerCount - 8;

			for (int i = 0; i < remainder; i++)
				rightGroup.get(i).getRenderable().setVisible(true);
			cardLayout.show(renderable, dual);
		}
	}

	public JPanel getRenderable()
	{
		return renderable;
	}
}
