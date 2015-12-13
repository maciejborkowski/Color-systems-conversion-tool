package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import engine.Converter;
import gui.listener.ColorChangeListener;
import gui.listener.ColorListenerManager;

@SuppressWarnings("serial")
public class ConversionPanel extends JPanel {
	private JLabel rgb = new JLabel("RGB");
	private JTextField red = new JTextField();
	private JTextField green = new JTextField();
	private JTextField blue = new JTextField();

	private JLabel cmyk = new JLabel("CMYK");
	private JTextField cyan = new JTextField();
	private JTextField magenta = new JTextField();
	private JTextField yellow = new JTextField();
	private JTextField key = new JTextField();

	private JLabel grayscale = new JLabel("Grayscale");
	private JTextField gray = new JTextField();

	private JLabel hsv = new JLabel("HSV");
	private JTextField hue = new JTextField();
	private JTextField saturation = new JTextField();
	private JTextField value = new JTextField();

	private final NumberFormat formatter = new DecimalFormat("#0.00");

	public ConversionPanel(ColorListenerManager colorlistenerManager) {
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.gridx = 0;
		addComponent(rgb, constraints);
		newLine(constraints);
		addComponent(red, constraints);
		addComponent(green, constraints);
		addComponent(blue, constraints);

		newLine(constraints);
		addComponent(cmyk, constraints);
		newLine(constraints);
		addComponent(cyan, constraints);
		addComponent(magenta, constraints);
		addComponent(yellow, constraints);
		addComponent(key, constraints);

		newLine(constraints);
		addComponent(grayscale, constraints);
		newLine(constraints);
		addComponent(gray, constraints);

		newLine(constraints);
		addComponent(hsv, constraints);
		newLine(constraints);
		addComponent(hue, constraints);
		addComponent(saturation, constraints);
		addComponent(value, constraints);

		RGBListener rgbListener = new RGBListener(colorlistenerManager);
		red.getDocument().addDocumentListener(rgbListener);
		green.getDocument().addDocumentListener(rgbListener);
		blue.getDocument().addDocumentListener(rgbListener);

		CMYKListener cmykListener = new CMYKListener(colorlistenerManager);
		cyan.getDocument().addDocumentListener(cmykListener);
		magenta.getDocument().addDocumentListener(cmykListener);
		yellow.getDocument().addDocumentListener(cmykListener);
		key.getDocument().addDocumentListener(cmykListener);

		GrayscaleListener grayscaleListener = new GrayscaleListener(colorlistenerManager);
		gray.getDocument().addDocumentListener(grayscaleListener);

		HSVListener hsvListener = new HSVListener(colorlistenerManager);
		hue.getDocument().addDocumentListener(hsvListener);
		saturation.getDocument().addDocumentListener(hsvListener);
		value.getDocument().addDocumentListener(hsvListener);
	}

	public void setRGB(int red, int green, int blue) {
		this.red.setText(Integer.toString(red));
		this.green.setText(Integer.toString(green));
		this.blue.setText(Integer.toString(blue));
	}

	private void addComponent(Component field, GridBagConstraints constraints) {
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.9;
		field.setPreferredSize(new Dimension(50, 20));
		add(field, constraints);
		constraints.gridx++;
	}

	private void newLine(GridBagConstraints constraints) {
		constraints.gridy++;
		constraints.gridx = 0;
	}

	private class RGBListener extends ColorChangeListener {

		public RGBListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			if (red.getText().isEmpty() || green.getText().isEmpty() || blue.getText().isEmpty()) {
				return;
			}
			int redValue = Integer.parseInt(red.getText());
			int greenValue = Integer.parseInt(green.getText());
			int blueValue = Integer.parseInt(blue.getText());
			changeCMYK(redValue, greenValue, blueValue);
			changeGrayscale(redValue, greenValue, blueValue);
			changeHSV(redValue, greenValue, blueValue);
		}

		private void changeCMYK(int red, int green, int blue) {
			double[] cmyk = Converter.rgb2cmyk(red, green, blue);
			cyan.setText(formatter.format(cmyk[0]));
			magenta.setText(formatter.format(cmyk[1]));
			yellow.setText(formatter.format(cmyk[2]));
			key.setText(formatter.format(cmyk[3]));
		}

		private void changeGrayscale(int red, int green, int blue) {
			int grayValue = Converter.rgb2grayscale(red, green, blue);
			gray.setText(String.valueOf(grayValue));
		}

		private void changeHSV(int red, int green, int blue) {
			double[] hsv = Converter.rgb2hsv(red, green, blue);
			hue.setText(formatter.format(hsv[0]));
			saturation.setText(formatter.format(hsv[1]));
			value.setText(formatter.format(hsv[2]));
		}

		@Override
		protected boolean isMainColor() {
			return true;
		}
	}

	private class CMYKListener extends ColorChangeListener {

		public CMYKListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			if (cyan.getText().isEmpty() || magenta.getText().isEmpty() || yellow.getText().isEmpty()
					|| key.getText().isEmpty()) {
				return;
			}

			double cyanValue = Double.parseDouble(cyan.getText());
			double magentaValue = Double.parseDouble(magenta.getText());
			double yellowValue = Double.parseDouble(yellow.getText());
			double keyValue = Double.parseDouble(key.getText());
			int[] rgb = Converter.cmyk2rgb(cyanValue, magentaValue, yellowValue, keyValue);
			setRGB(rgb[0], rgb[1], rgb[2]);
		}
	}

	private class GrayscaleListener extends ColorChangeListener {

		public GrayscaleListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			if (gray.getText().isEmpty()) {
				return;
			}
			int gray = Integer.parseInt(cyan.getText());
			int[] rgb = Converter.grayscale2rgb(gray);
			setRGB(rgb[0], rgb[1], rgb[2]);
		}
	}

	private class HSVListener extends ColorChangeListener {

		public HSVListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			if (hue.getText().isEmpty() || saturation.getText().isEmpty() || value.getText().isEmpty()) {
				return;
			}

			double hueValue = Double.parseDouble(hue.getText());
			double saturationValue = Double.parseDouble(saturation.getText());
			double valueValue = Double.parseDouble(value.getText());
			int[] rgb = Converter.hsv2rgb(hueValue, saturationValue, valueValue);
			setRGB(rgb[0], rgb[1], rgb[2]);
		}
	}

}
