package gui.picker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ColoredStrip extends JComponent {
	private Color[] colors;
	private int width;
	private int height;

	public ColoredStrip(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (colors == null) {
			return;
		}
		double step = 1.0 * width / colors.length;
		double x = 0;
		int done = 0;
		for (int i = 0; i < colors.length; i++) {
			g.setColor(colors[i]);
			int w = 0;
			while (x + step > done + w) {
				w++;
			}
			g.fillRect(done, 0, w, height);
			x += step;
			done += w;
		}
	}

	public void setColors(Color[] colors) {
		this.colors = colors;
	}
}
