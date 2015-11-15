package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.opencv.core.Core;

public class Application {
	private final static String WINDOW_LABEL = "Color Systems Conversion Tool";

	private JFrame frame;
	private ConversionPanel panel;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
		initializeApplication();
	}

	private void initializeApplication() {
		initFrame();
	}

	private void initFrame() {
		frame = new JFrame(WINDOW_LABEL);
		frame.setBounds(0, 0, 800, 680);
		frame.getContentPane().setLayout(new BorderLayout());
		panel = new ConversionPanel();
		frame.add(panel, BorderLayout.LINE_START);
	}

}