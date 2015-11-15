package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class ImageChooserListener implements ActionListener {
	private JFileChooser fileChooser = new JFileChooser();
	private final ImagePanel imagePanel;

	public ImageChooserListener(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fileChooser.setCurrentDirectory(new File("E:/workspaceMars/SOMThermalFaces/pictures"));
		int returnVal = fileChooser.showOpenDialog(imagePanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File image = fileChooser.getSelectedFile();
			try {
				imagePanel.setImage(ImageIO.read(image));
				imagePanel.repaint();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
