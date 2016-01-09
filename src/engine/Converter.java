package engine;

public class Converter {

	public static double[] rgb2cmyk(int red, int green, int blue) {
		double scaledRed = (double) red / 255;
		double scaledGreen = (double) green / 255;
		double scaledBlue = (double) blue / 255;

		double K = 1 - maxOfThree(scaledRed, scaledGreen, scaledBlue);
		double divider = Math.max(1 - K, 0.0001);
		double C = (1 - scaledRed - K) / divider;
		double M = (1 - scaledGreen - K) / divider;
		double Y = (1 - scaledBlue - K) / divider;

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

	public static double[] rgb2hsv(int red, int green, int blue) {
		double scaledRed = (double) red / 255;
		double scaledGreen = (double) green / 255;
		double scaledBlue = (double) blue / 255;

		double Cmax = maxOfThree(scaledRed, scaledGreen, scaledBlue);
		double Cmin = minOfThree(scaledRed, scaledGreen, scaledBlue);
		double delta = Cmax - Cmin;

		double hue = -1;

		if (delta == 0) {
			hue = 0;
		} else if (Cmax == scaledRed) {
			hue = 60 * (((scaledGreen - scaledBlue) / delta) % 6);
		} else if (Cmax == scaledGreen) {
			hue = 60 * (((scaledBlue - scaledRed) / delta) + 2);
		} else if (Cmax == scaledBlue) {
			hue = 60 * (((scaledRed - scaledGreen) / delta) + 4);
		}

		hue = (hue + 360) % 360;

		double saturation = (Cmax == 0) ? 0 : delta / Cmax;
		double value = Cmax;

		return new double[] { hue, saturation * 100, value * 100 };
	}

	public static int[] hsv2rgb(double hue, double saturation, double value) {
		hue = hue % 360;

		saturation /= 100;
		value /= 100;

		double c = value * saturation;
		double x = c * (1 - Math.abs((hue / 60) % 2 - 1));
		double m = value - c;

		double scaledRGB[];
		if (0 <= hue && hue < 60) {
			scaledRGB = new double[] { c, x, 0 };
		} else if (60 <= hue && hue < 120) {
			scaledRGB = new double[] { x, c, 0 };
		} else if (120 <= hue && hue < 180) {
			scaledRGB = new double[] { 0, c, x };
		} else if (180 <= hue && hue < 240) {
			scaledRGB = new double[] { 0, x, c };
		} else if (240 <= hue && hue < 300) {
			scaledRGB = new double[] { x, 0, c };
		} else { // if (300 <= hue && hue < 360)
			scaledRGB = new double[] { c, 0, x };
		}

		int red = (int) ((scaledRGB[0] + m) * 255);
		int green = (int) ((scaledRGB[1] + m) * 255);
		int blue = (int) ((scaledRGB[2] + m) * 255);

		return new int[] { red, green, blue };
	}

	public static boolean rgb2binary(int red, int green, int blue) {
		// TODO
		return false;
	}

	public static int[] binary2rgb(boolean binary) {
		// TODO
		return null;
	}

	public static double[] rgb2yuv(int red, int green, int blue) {
		double y = (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
		double u = (-0.146 * red - 0.288 * green - 0.434 * blue) / 255;
		double v = (0.617 * red - 0.517 * green + 0.100 * blue) / 255;

		return new double[] { y, u, v };
	}

	public static int[] yuv2rgb(double y, double u, double v) {
		int r = (int) ((1.4245 * y + 0.6619 * u + 1.2487 * v) * 255);
		int g = (int) ((1.4245 * y + 0.2669 * u - 0.4655 * v) * 255);
		int b = (int) ((-1.4245 * y - 2.7039 * u - 0.1111 * v) * 255);

		return new int[] { r, g, b };
	}

	public static double[] rgb2yiq(int red, int green, int blue) {
		double y = (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
		double i = (-0.168 * red - 0.257 * green - 0.321 * blue) / 255;
		double q = (0.212 * red - 0.528 * green + 0.311 * blue) / 255;

		return new double[] { y, i, q };
	}

	public static int[] yiq2rgb(double y, double i, double q) {
		int r = (int) ((4.9814 * y + 4.8482 * i + 3.1782 * q) * 255);
		int g = (int) ((0.3156 * y - 0.9397 * i - 1.0856 * q) * 255);
		int b = (int) ((-2.8598 * y - 4.9003 * i - 0.7942 * q) * 255);

		return new int[] { r, g, b };
	}

	public static String rgb2hex(int r, int g, int b) {
		String hexR = Integer.toHexString(r);
		String hexG = Integer.toHexString(g);
		String hexB = Integer.toHexString(b);

		hexR = hexR.length() < 2 ? "0" + hexR : hexR;
		hexG = hexG.length() < 2 ? "0" + hexG : hexG;
		hexB = hexB.length() < 2 ? "0" + hexB : hexB;

		return "#" + hexR + hexG + hexB;
	}

	public static int[] hex2rgb(String hex) {
		String hexR = hex.substring(1, 3);
		String hexG = hex.substring(3, 5);
		String hexB = hex.substring(5, 7);
		
		return new int[] {Integer.parseInt(hexR, 16), Integer.parseInt(hexG, 16), Integer.parseInt(hexB, 16)};
	}

	private static double maxOfThree(double first, double second, double third) {
		return Math.max(first, Math.max(second, third));
	}

	private static double minOfThree(double first, double second, double third) {
		return Math.min(first, Math.min(second, third));
	}

}
