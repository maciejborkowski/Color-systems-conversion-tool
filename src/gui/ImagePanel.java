package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements MouseListener {
	private ConversionPanel conversionPanel;

	public ImagePanel(ConversionPanel conversionPanel) {
		this.conversionPanel = conversionPanel;
		this.addMouseListener(this);
	}

	private BufferedImage image;

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
			System.out.println(point + " " + pixel);
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
