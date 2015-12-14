package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import engine.Converter;
import gui.listener.ClickListener;

@SuppressWarnings("serial")
public class HSVPicker extends JComponent implements ClickListener {
	private final ConversionPanel conversionPanel;
	private double hue;
	private int colors[][][];
	private int saturationResolution;
	private int valueResolution;

	public HSVPicker(ConversionPanel conversionPanel, int saturationResolution, int valueResolution) {
		this.conversionPanel = conversionPanel;
		this.saturationResolution = saturationResolution;
		this.valueResolution = valueResolution;
		colors = new int[saturationResolution][valueResolution][3];
		setPreferredSize(new Dimension(saturationResolution, valueResolution));
		setHue(0);
		this.addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		for (int j = 0; j < valueResolution; j++) {
			for (int i = 0; i < saturationResolution - j; i++) {
				Color color = new Color(colors[i][j][0], colors[i][j][1], colors[i][j][2]);
				g.setColor(color);
				g.fillRect(i, j, 1, 1);
			}
		}
	}

	public void setHue(double hue) {
		this.hue = hue;
		for (int j = 0; j < valueResolution; j++) {
			int localSatRes = (int) (saturationResolution * (1.0 - 1.0 * j / valueResolution));
			for (int i = 0; i < localSatRes; i++) {
				int[] rgb = Converter.hsv2rgb(hue, 100.0 * i / localSatRes, 100 * (1.0 - 1.0 * j / valueResolution));
				colors[i][j] = rgb;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		try {
			int y = point.y;
			int x = point.x;
			conversionPanel.setRGB(colors[x][y][0], colors[x][y][1], colors[x][y][2]);
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
	}
}