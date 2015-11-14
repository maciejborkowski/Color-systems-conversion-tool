package engine;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Converter {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static Mat convertRGBtoCMYK(Mat rgb) {
		// Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		// System.out.println("mat = " + mat.dump());

		Mat cmyk = new Mat(rgb.rows(), rgb.cols(), CvType.CV_8UC4);
		byte rgbBuff[] = new byte[(int) (rgb.total() * rgb.channels())];
		rgb.get(0, 0, rgbBuff);
		byte cmykBuff[] = new byte[(int) (cmyk.total() * cmyk.channels())];
		cmyk.get(0, 0, cmykBuff);

		for (int i = 0; i < cmyk.total(); i++) {
			double r = rgbBuff[(int) i] / 255.0;
			double g = rgbBuff[(int) (1 * rgb.total() + i)] / 255.0;
			double b = rgbBuff[(int) (2 * rgb.total() + i)] / 255.0;

			double k = Math.min(Math.min(1.0 - r, 1.0 - g), 1.0 - b);

			cmykBuff[i] = (byte) ((1.0 - r - k) / (1.0 - k) * 255.0);
			cmykBuff[(int) (1 * rgb.total() + i)] = (byte) ((1.0 - g - k) / (1.0 - k) * 255.0);
			cmykBuff[(int) (2 * rgb.total() + i)] = (byte) ((1.0 - b - k) / (1.0 - k) * 255.0);
			cmykBuff[(int) (3 * rgb.total() + i)] = (byte) (k * 255.0);
		}

		cmyk.put(0, 0, cmykBuff);
		return cmyk;
	}

}
