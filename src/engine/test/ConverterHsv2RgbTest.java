package engine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Converter;

public class ConverterHsv2RgbTest {
	double hsv[];
	int expecteds[], actuals[];

	@Test
	public void shouldConvertBlack() {
		hsv = new double[] { 0, 0, 0 };
		expecteds = new int[] { 0, 0, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertWhite() {
		hsv = new double[] { 0, 0, 100 };
		expecteds = new int[] { 255, 255, 255 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertRed() {
		hsv = new double[] { 0, 100, 100 };
		expecteds = new int[] { 255, 0, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertLime() {
		hsv = new double[] { 120, 100, 100 };
		expecteds = new int[] { 0, 255, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertBlue() {
		hsv = new double[] { 240, 100, 100 };
		expecteds = new int[] { 0, 0, 255 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertYellow() {
		hsv = new double[] { 60, 100, 100 };
		expecteds = new int[] { 255, 255, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertCyan() {
		hsv = new double[] { 180, 100, 100 };
		expecteds = new int[] { 0, 255, 255 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertMagenta() {
		hsv = new double[] { 300, 100, 100 };
		expecteds = new int[] { 255, 0, 255 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertSilver() {
		hsv = new double[] { 0, 0, 75 };
		expecteds = new int[] { 191, 191, 191 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertGray() {
		hsv = new double[] { 0, 0, 50 };
		expecteds = new int[] { 127, 127, 127 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertMaroon() {
		hsv = new double[] { 0, 100, 50 };
		expecteds = new int[] { 127, 0, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertOlive() {
		hsv = new double[] { 60, 100, 50 };
		expecteds = new int[] { 127, 127, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertGreen() {
		hsv = new double[] { 120, 100, 50 };
		expecteds = new int[] { 0, 127, 0 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertPurple() {
		hsv = new double[] { 300, 100, 50 };
		expecteds = new int[] { 127, 0, 127 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertTeal() {
		hsv = new double[] { 180, 100, 50 };
		expecteds = new int[] { 0, 127, 127 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void shouldConvertNavy() {
		hsv = new double[] { 240, 100, 50 };
		expecteds = new int[] { 0, 0, 127 };
		actuals = Converter.hsv2rgb(hsv[0], hsv[1], hsv[2]);
		assertArrayEquals(expecteds, actuals);
	}
}
