package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ColoredSquare extends JComponent {
	private Color color;
	private int size;

	public ColoredSquare(int size, Color color) {
		this.size = size;
		this.color = color;
		setPreferredSize(new Dimension(size, size));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, size, size);
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
