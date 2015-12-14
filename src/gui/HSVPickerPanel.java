package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class HSVPickerPanel extends JPanel {
	private static final int SLIDER_HEIGHT = 10;
	private static final int FULL_ROTATION = 360;
	private HSVPicker hsvPicker;
	private JSlider hueSlider;
	private int height = 400;
	private int width = 400;

	public HSVPickerPanel(ConversionPanel conversionPanel) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		hsvPicker = new HSVPicker(conversionPanel, width, height);
		hueSlider = new JSlider(JSlider.HORIZONTAL, 0, FULL_ROTATION, 0);
		hueSlider.setMaximumSize(new Dimension(width, SLIDER_HEIGHT));
		hueSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
		hueSlider.setAlignmentY(Component.TOP_ALIGNMENT);
		add(hueSlider);
		add(hsvPicker);
		hueSlider.addMouseMotionListener(new HueChangeListener());
	}

	private class HueChangeListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			JSlider source = (JSlider) e.getSource();
			int hue = (int) source.getValue();
			hsvPicker.setHue(1.0 * hue);
			hsvPicker.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

}
