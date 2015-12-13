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
	private HSVPicker hsvPicker;
	private JSlider hueSlider;

	public HSVPickerPanel(ConversionPanel conversionPanel) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		hsvPicker = new HSVPicker(conversionPanel, 15, 20, 20);
		hueSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		hueSlider.setMaximumSize(new Dimension(15 * 20, 10));
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
