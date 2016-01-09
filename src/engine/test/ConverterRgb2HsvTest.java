package engine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import engine.Converter;

public class ConverterRgb2HsvTest {
	int rgb[];
	double expecteds[], actuals[];
	double delta = 1;

	@After
	public void computeAndCheck() {
		actuals = Converter.rgb2hsv(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}

	@Test
	public void shouldConvertBlack() {
		rgb = new int[] { 0, 0, 0 };
		expecteds = new double[] { 0, 0, 0 };
	}

	@Test
	public void shouldConvertWhite() {
		rgb = new int[] { 255, 255, 255 };
		expecteds = new double[] { 0, 0, 100 };
	}

	@Test
	public void shouldConvertRed() {
		rgb = new int[] { 255, 0, 0 };
		expecteds = new double[] { 0, 100, 100 };
	}

	@Test
	public void shouldConvertLime() {
		rgb = new int[] { 0, 255, 0 };
		expecteds = new double[] { 120, 100, 100 };
	}

	@Test
	public void shouldConvertBlue() {
		rgb = new int[] { 0, 0, 255 };
		expecteds = new double[] { 240, 100, 100 };
	}

	@Test
	public void shouldConvertYellow() {
		rgb = new int[] { 255, 255, 0 };
		expecteds = new double[] { 60, 100, 100 };
	}

	@Test
	public void shouldConvertCyan() {
		rgb = new int[] { 0, 255, 255 };
		expecteds = new double[] { 180, 100, 100 };
	}

	@Test
	public void shouldConvertMagenta() {
		rgb = new int[] { 255, 0, 255 };
		expecteds = new double[] { 300, 100, 100 };
	}

	@Test
	public void shouldConvertSilver() {
		rgb = new int[] { 192, 192, 192 };
		expecteds = new double[] { 0, 0, 75 };
	}

	@Test
	public void shouldConvertGray() {
		rgb = new int[] { 128, 128, 128 };
		expecteds = new double[] { 0, 0, 50 };
	}

	@Test
	public void shouldConvertMaroon() {
		rgb = new int[] { 128, 0, 0 };
		expecteds = new double[] { 0, 100, 50 };
	}

	@Test
	public void shouldConvertOlive() {
		rgb = new int[] { 128, 128, 0 };
		expecteds = new double[] { 60, 100, 50 };
	}

	@Test
	public void shouldConvertGreen() {
		rgb = new int[] { 0, 128, 0 };
		expecteds = new double[] { 120, 100, 50 };
	}

	@Test
	public void shouldConvertPurple() {
		rgb = new int[] { 128, 0, 128 };
		expecteds = new double[] { 300, 100, 50 };
	}

	@Test
	public void shouldConvertTeal() {
		rgb = new int[] { 0, 128, 128 };
		expecteds = new double[] { 180, 100, 50 };
	}

	@Test
	public void shouldConvertNavy() {
		rgb = new int[] { 0, 0, 128 };
		expecteds = new double[] { 240, 100, 50 };
	}
}
