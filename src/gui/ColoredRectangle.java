package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ColoredRectangle extends JComponent {
	private Color color;
	private Dimension dimsension;

	public ColoredRectangle(Dimension dimsension, Color color) {
		this.dimsension = dimsension;
		this.color = color;
		setPreferredSize(new Dimension(dimsension.width, dimsension.height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, dimsension.width, dimsension.height);
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
