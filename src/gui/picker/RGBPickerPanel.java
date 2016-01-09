package gui.picker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;

import gui.ConversionPanel;
import gui.listener.DragListener;

@SuppressWarnings("serial")
public class RGBPickerPanel extends JPanel {
	private ConversionPanel conversionPanel;
	private static final int FULL_ROTATION = 255;
	private static final int SLIDER_HEIGHT = 30;
	private static final int STRIP_HEIGHT = 70;
	private int width = 500;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private ColoredStrip redStrip;
	private ColoredStrip greenStrip;
	private ColoredStrip blueStrip;

	public RGBPickerPanel(ConversionPanel conversionPanel) {
		this.conversionPanel = conversionPanel;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		redSlider = initSlider();
		redStrip = initStrip();
		redStrip.setColors(createColors(-1, 0, 0));
		redSlider.addMouseMotionListener(new SliderChangeListener(redStrip));

		greenSlider = initSlider();
		greenStrip = initStrip();
		greenStrip.setColors(createColors(0, -1, 0));
		greenSlider.addMouseMotionListener(new SliderChangeListener(greenStrip));

		blueSlider = initSlider();
		blueStrip = initStrip();
		blueStrip.setColors(createColors(0, 0, -1));
		blueSlider.addMouseMotionListener(new SliderChangeListener(blueStrip));
	}

	public void setColor(int red, int green, int blue) {
		redSlider.setValue(red);
		redStrip.setColors(createColors(-1, green, blue));
		greenSlider.setValue(green);
		greenStrip.setColors(createColors(red, -1, blue));
		blueSlider.setValue(blue);
		blueStrip.setColors(createColors(red, green, -1));
		repaint();
	}

	private JSlider initSlider() {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, FULL_ROTATION, 0);
		slider.setMaximumSize(new Dimension(width, SLIDER_HEIGHT));
		slider.setAlignmentX(Component.LEFT_ALIGNMENT);
		slider.setAlignmentY(Component.TOP_ALIGNMENT);
		add(slider);
		return slider;
	}

	private ColoredStrip initStrip() {
		ColoredStrip strip = new ColoredStrip(width, STRIP_HEIGHT);
		strip.setMaximumSize(new Dimension(width * 2, STRIP_HEIGHT));
		add(strip);
		return strip;
	}

	private class SliderChangeListener implements DragListener {
		public final ColoredStrip excludedStrip;

		public SliderChangeListener(ColoredStrip excludedStrip) {
			this.excludedStrip = excludedStrip;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int red = redSlider.getValue();
			int green = greenSlider.getValue();
			int blue = blueSlider.getValue();
			conversionPanel.setRGB(red, green, blue);
			repaintStrips(red, green, blue);
		}

		private void repaintStrips(int red, int green, int blue) {
			if (excludedStrip != redStrip) {
				redStrip.setColors(createColors(-1, green, blue));
				redStrip.repaint();
			}
			if (excludedStrip != greenStrip) {
				greenStrip.setColors(createColors(red, -1, blue));
				greenStrip.repaint();
			}
			if (excludedStrip != blueStrip) {
				blueStrip.setColors(createColors(red, green, -1));
				blueStrip.repaint();
			}
		}

	}

	private Color[] createColors(int red, int green, int blue) {
		Color[] colors = new Color[256];
		for (int i = 0; i < 256; i++) {
			if (red == -1) {
				colors[i] = new Color(i, green, blue);
			} else if (green == -1) {
				colors[i] = new Color(red, i, blue);
			} else if (blue == -1) {
				colors[i] = new Color(red, green, i);
			}
		}
		return colors;
	}

}
