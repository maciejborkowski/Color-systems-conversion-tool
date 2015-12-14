package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import engine.Converter;
import gui.listener.ColorChangeListener;
import gui.listener.ColorListenerManager;

@SuppressWarnings("serial")
public class ConversionPanel extends JPanel {
	private static final int INDICATOR_SIZE = 200;
	private ColoredSquare colorIndicator = new ColoredSquare(INDICATOR_SIZE, Color.WHITE);

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

	private final NumberFormat cmykFormetter = NumberFormat.getNumberInstance(Locale.ENGLISH);
	private final NumberFormat hsvFormatter = NumberFormat.getNumberInstance(Locale.ENGLISH);
	private ColorListenerManager colorListenerManager;

	public ConversionPanel(ColorListenerManager colorListenerManager) {
		this.colorListenerManager = colorListenerManager;
		cmykFormetter.setMaximumFractionDigits(2);
		hsvFormatter.setMaximumFractionDigits(0);
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.gridx = 0;

		addIndicator(constraints);
		addRGB(constraints);
		addCMYK(constraints);
		addGrayscale(constraints);
		addHSV(constraints);
	}

	private void addIndicator(GridBagConstraints constraints) {
		constraints.weightx = 1.0;
		constraints.gridwidth = 4;
		addComponent(colorIndicator, constraints);
		newLine(constraints);
	}

	private void addRGB(GridBagConstraints constraints) {
		addComponent(rgb, constraints);
		newLine(constraints);
		addComponent(red, constraints);
		addComponent(green, constraints);
		addComponent(blue, constraints);
		newLine(constraints);

		RGBListener rgbListener = new RGBListener(colorListenerManager);
		red.getDocument().addDocumentListener(rgbListener);
		green.getDocument().addDocumentListener(rgbListener);
		blue.getDocument().addDocumentListener(rgbListener);
	}

	private void addCMYK(GridBagConstraints constraints) {
		addComponent(cmyk, constraints);
		newLine(constraints);
		addComponent(cyan, constraints);
		addComponent(magenta, constraints);
		addComponent(yellow, constraints);
		addComponent(key, constraints);
		newLine(constraints);

		CMYKListener cmykListener = new CMYKListener(colorListenerManager);
		cyan.getDocument().addDocumentListener(cmykListener);
		magenta.getDocument().addDocumentListener(cmykListener);
		yellow.getDocument().addDocumentListener(cmykListener);
		key.getDocument().addDocumentListener(cmykListener);

	}

	private void addGrayscale(GridBagConstraints constraints) {
		addComponent(grayscale, constraints);
		newLine(constraints);
		addComponent(gray, constraints);
		newLine(constraints);

		GrayscaleListener grayscaleListener = new GrayscaleListener(colorListenerManager);
		gray.getDocument().addDocumentListener(grayscaleListener);

	}

	private void addHSV(GridBagConstraints constraints) {
		addComponent(hsv, constraints);
		newLine(constraints);
		addComponent(hue, constraints);
		addComponent(saturation, constraints);
		addComponent(value, constraints);
		newLine(constraints);

		HSVListener hsvListener = new HSVListener(colorListenerManager);
		hue.getDocument().addDocumentListener(hsvListener);
		saturation.getDocument().addDocumentListener(hsvListener);
		value.getDocument().addDocumentListener(hsvListener);
	}

	public void setRGB(int red, int green, int blue) {
		this.red.setText(Integer.toString(red));
		this.green.setText(Integer.toString(green));
		this.blue.setText(Integer.toString(blue));
	}

	private void addComponent(Component component, GridBagConstraints constraints) {
		constraints.fill = GridBagConstraints.NONE;
		if (component instanceof JTextField) {
			constraints.gridwidth = 1;
			component.setPreferredSize(new Dimension(60, 20));
		} else if (component instanceof JLabel) {
			constraints.gridwidth = 1;
			component.setPreferredSize(new Dimension(60, 20));
		}
		add(component, constraints);
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
			try {
				int redValue = Integer.parseInt(red.getText());
				int greenValue = Integer.parseInt(green.getText());
				int blueValue = Integer.parseInt(blue.getText());
				validateBetween(0, 255, redValue, greenValue, blueValue);
				changeIndicator(redValue, greenValue, blueValue);
				changeCMYK(redValue, greenValue, blueValue);
				changeGrayscale(redValue, greenValue, blueValue);
				changeHSV(redValue, greenValue, blueValue);
			} catch (NumberFormatException ex) {
			}
		}

		private void changeIndicator(int red, int green, int blue) {
			Color color = new Color(red, green, blue);
			colorIndicator.setColor(color);
			colorIndicator.repaint();
		}

		private void changeCMYK(int red, int green, int blue) {
			double[] cmyk = Converter.rgb2cmyk(red, green, blue);
			cyan.setText(cmykFormetter.format(cmyk[0]));
			magenta.setText(cmykFormetter.format(cmyk[1]));
			yellow.setText(cmykFormetter.format(cmyk[2]));
			key.setText(cmykFormetter.format(cmyk[3]));
		}

		private void changeGrayscale(int red, int green, int blue) {
			int grayValue = Converter.rgb2grayscale(red, green, blue);
			gray.setText(String.valueOf(grayValue));
		}

		private void changeHSV(int red, int green, int blue) {
			double[] hsv = Converter.rgb2hsv(red, green, blue);
			hue.setText(hsvFormatter.format(hsv[0]));
			saturation.setText(hsvFormatter.format(hsv[1]));
			value.setText(hsvFormatter.format(hsv[2]));
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
			try {
				double cyanValue = Double.parseDouble(cyan.getText());
				double magentaValue = Double.parseDouble(magenta.getText());
				double yellowValue = Double.parseDouble(yellow.getText());
				double keyValue = Double.parseDouble(key.getText());
				validateBetween(0.0, 1.0, cyanValue, magentaValue, yellowValue, keyValue);
				int[] rgb = Converter.cmyk2rgb(cyanValue, magentaValue, yellowValue, keyValue);
				setRGB(rgb[0], rgb[1], rgb[2]);
			} catch (NumberFormatException ex) {
			}
		}
	}

	private class GrayscaleListener extends ColorChangeListener {

		public GrayscaleListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			try {
				int grayValue = Integer.parseInt(gray.getText());
				validateBetween(0, 255, grayValue);
				int[] rgb = Converter.grayscale2rgb(grayValue);
				setRGB(rgb[0], rgb[1], rgb[2]);
			} catch (NumberFormatException ex) {
			}
		}
	}

	private class HSVListener extends ColorChangeListener {

		public HSVListener(ColorListenerManager manager) {
			super(manager);
		}

		@Override
		protected void stateChanged(DocumentEvent e) {
			try {
				double hueValue = Double.parseDouble(hue.getText());
				double saturationValue = Double.parseDouble(saturation.getText());
				double valueValue = Double.parseDouble(value.getText());
				validateBetween(0.0, 255.0, hueValue, saturationValue, valueValue);
				int[] rgb = Converter.hsv2rgb(hueValue, saturationValue, valueValue);
				setRGB(rgb[0], rgb[1], rgb[2]);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void validateBetween(int from, int to, int... values) {
		for (int value : values) {
			if (value < from || value > to) {
				throw new NumberFormatException("Value " + value + " is not between " + from + " and " + to);
			}
		}
	}

	public void validateBetween(double from, double to, double... values) {
		for (double value : values) {
			if (value < from || value > to) {
				throw new NumberFormatException("Value " + value + " is not between " + from + " and " + to);
			}
		}
	}
}
