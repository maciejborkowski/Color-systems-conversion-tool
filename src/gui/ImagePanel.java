package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gui.listener.ClickListener;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements ClickListener {
	private ConversionPanel conversionPanel;

	public ImagePanel(ConversionPanel conversionPanel) {
		this.conversionPanel = conversionPanel;
		this.addMouseListener(this);
	}

	private BufferedImage image;

	@Override
	public Dimension getPreferredSize() {
		if (image != null) {
			return new Dimension(image.getWidth(), image.getHeight());
		}
		return new Dimension(0, 0);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (image == null) {
			return;
		}
		Point point = e.getPoint();
		try {
			int[] pixel = image.getData().getPixel(point.x, point.y, new int[3]);
			conversionPanel.setRGB(pixel[0], pixel[1], pixel[2]);
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
	}

}
