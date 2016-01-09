package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.opencv.core.Core;

import gui.listener.ColorListenerManager;
import gui.listener.ImageChooserListener;
import gui.picker.HSVPickerPanel;
import gui.picker.RGBPickerPanel;

public class ColorConversionApplication {
	private final static String WINDOW_LABEL = "Color Systems Conversion Tool";
	private final static String MENU_IMAGE = "Image";
	private final static String MENU_LOAD = "Load";

	private JFrame frame;
	private ConversionPanel conversionPanel;
	private JTabbedPane tabs;
	private ImagePanel imagePanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private final ColorListenerManager colorListenerManager = new ColorListenerManager();
	private HSVPickerPanel hsvPickerPanel;
	private RGBPickerPanel rgbPickerPanel;
	private JScrollPane scrollableImage;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorConversionApplication window = new ColorConversionApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ColorConversionApplication() {
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
		tabs = new JTabbedPane();
		conversionPanel = new ConversionPanel(colorListenerManager);
		hsvPickerPanel = new HSVPickerPanel(conversionPanel);
		conversionPanel.setHSVPicker(hsvPickerPanel);
		rgbPickerPanel = new RGBPickerPanel(conversionPanel);
		conversionPanel.setRGBPicker(rgbPickerPanel);

		imagePanel = new ImagePanel(conversionPanel);
		scrollableImage = new JScrollPane(imagePanel);
		tabs.addTab("HSV Picker", hsvPickerPanel);
		tabs.addTab("RGB Picker", rgbPickerPanel);
		tabs.addTab("Image Picker", scrollableImage);
		frame.add(conversionPanel, BorderLayout.WEST);
		frame.add(tabs, BorderLayout.CENTER);
	}

}