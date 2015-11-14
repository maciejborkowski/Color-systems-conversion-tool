package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import engine.Converter;

@SuppressWarnings("serial")
public class ConversionPanel extends JPanel {
	private JLabel rgb = new JLabel("RGB");
	private JLabel redLabel = new JLabel("red");
	private JTextField red = new JTextField();
	private JLabel greenLabel = new JLabel("green");
	private JTextField green = new JTextField();
	private JLabel blueLabel = new JLabel("blue");
	private JTextField blue = new JTextField();

	private JLabel cmyk = new JLabel("CMYK");
	private JLabel cyanLabel = new JLabel("cyan");
	private JTextField cyan = new JTextField();
	private JLabel magentaLabel = new JLabel("magenta");
	private JTextField magenta = new JTextField();
	private JLabel yellowLabel = new JLabel("yellow");
	private JTextField yellow = new JTextField();
	private JLabel keyLabel = new JLabel("key");
	private JTextField key = new JTextField();

	public ConversionPanel() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		addComponent(rgb, constraints);
		addComponent(redLabel, constraints);
		addComponent(red, constraints);
		addComponent(greenLabel, constraints);
		addComponent(green, constraints);
		addComponent(blueLabel, constraints);
		addComponent(blue, constraints);
		addComponent(cmyk, constraints);
		addComponent(cyanLabel, constraints);
		addComponent(cyan, constraints);
		addComponent(magentaLabel, constraints);
		addComponent(magenta, constraints);
		addComponent(yellowLabel, constraints);
		addComponent(yellow, constraints);
		addComponent(keyLabel, constraints);
		addComponent(key, constraints);

		RGBListener rgbListener = new RGBListener();
		red.getDocument().addDocumentListener(rgbListener);
		green.getDocument().addDocumentListener(rgbListener);
		blue.getDocument().addDocumentListener(rgbListener);
	}

	private void addComponent(Component field, GridBagConstraints constraints) {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.1;
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 0.9;
		constraints.gridx = 1;
		add(field, constraints);
	}

	private class RGBListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			change();
		}

		public void removeUpdate(DocumentEvent e) {
			change();
		}

		public void insertUpdate(DocumentEvent e) {
			change();
		}

		private void change() {
			if (red.getText().isEmpty() || green.getText().isEmpty() || blue.getText().isEmpty()) {
				return;
			}
			Mat rgbMat = new Mat(1, 1, CvType.CV_8UC3);
			byte[] rgbValues = new byte[3];
			rgbValues[0] = (byte) Integer.parseInt(red.getText());
			rgbValues[1] = (byte) Integer.parseInt(green.getText());
			rgbValues[2] = (byte) Integer.parseInt(blue.getText());
			rgbMat.put(0, 0, rgbValues);
			Mat cmykMat = Converter.convertRGBtoCMYK(rgbMat);
			byte[] buff = new byte[4];
			cmykMat.get(0, 0, buff);
			cyan.setText(String.valueOf(buff[0] & 0xFF));
			magenta.setText(String.valueOf(buff[1] & 0xFF));
			yellow.setText(String.valueOf(buff[2] & 0xFF));
			key.setText(String.valueOf(buff[3] & 0xFF));
			
//			double cmyk[] = Converter.rgb2cmyk(rgbValues[0], rgbValues[1], rgbValues[2]);
//			cyan.setText(String.valueOf(cmyk[0]));
//			magenta.setText(String.valueOf(cmyk[1]));
//			yellow.setText(String.valueOf(cmyk[2]));
//			key.setText(String.valueOf(cmyk[3]));
		}
	}

}
