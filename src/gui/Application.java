package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.opencv.core.Core;

public class Application {
	private final static String WINDOW_LABEL = "Color Systems Conversion Tool";
	private final static String MENU_IMAGE = "Image";
	private final static String MENU_LOAD = "Load";

	private JFrame frame;
	private ConversionPanel conversionPanel;
	private ImagePanel imagePanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private final ColorListenerManager colorListenerManager = new ColorListenerManager();

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
		initMenuBar();
	}

	private void initMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu(MENU_IMAGE);
		menuBar.add(menu);
		menuItem = new JMenuItem(MENU_LOAD);
		menuItem.addActionListener(new ImageChooserListener(imagePanel));
		menu.add(menuItem);
		frame.setJMenuBar(menuBar);
	}

	private void initFrame() {
		frame = new JFrame(WINDOW_LABEL);
		frame.setBounds(0, 0, 800, 680);
		frame.getContentPane().setLayout(new BorderLayout());
		conversionPanel = new ConversionPanel(colorListenerManager);
		imagePanel = new ImagePanel(conversionPanel);
		frame.add(conversionPanel, BorderLayout.LINE_START);
		frame.add(imagePanel, BorderLayout.CENTER);
	}

}