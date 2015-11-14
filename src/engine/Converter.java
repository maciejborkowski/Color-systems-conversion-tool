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

	public static double[] rgb2cmyk(int red, int green, int blue) {
		double redDouble = (double) red / 255;
		double greenDouble = (double) green / 255;
		double blueDouble = (double) blue / 255;

		double K = 1 - Math.max(redDouble, Math.max(greenDouble, blueDouble));
		double divider = Math.max(1-K, 0.0001);
		double C = (1 - redDouble - K) / divider;
		double M = (1 - greenDouble - K) / divider;
		double Y = (1 - blueDouble - K) / divider;

		return new double[] { C, M, Y, K };
	}

	public static int[] cmyk2rgb(double cyan, double magenta, double yellow, double key) {
		int red = (int) (255 * (1 - cyan) * (1 - key));
		int green = (int) (255 * (1 - magenta) * (1 - key));
		int blue = (int) (255 * (1 - yellow) * (1 - key));

		return new int[] { red, green, blue };
	}

	public static int rgb2grayscale(int red, int green, int blue) {
		return (red + green + blue) / 3;
		// return (int) (0.2126*red+0.7152*green+0.0722*blue);
		// return (int)(0.299*red+0.587*green+0.114*blue);
	}

	public static int[] grayscale2rgb(int gray) {
		return new int[] { gray, gray, gray };
	}
}
