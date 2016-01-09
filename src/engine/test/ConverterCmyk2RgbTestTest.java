package engine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import engine.Converter;

public class ConverterCmyk2RgbTestTest {
	double cmyk[];
	int expecteds[], actuals[];

	@After
	public void computeAndCheck() {
		actuals = Converter.cmyk2rgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void shouldConvertBlack() {
		cmyk = new double[] { 0, 0, 0, 1 };
		expecteds = new int[] { 0, 0, 0 };
	}


	@Test
	public void shouldConvertWhite() {
		cmyk = new double[] { 0, 0, 0, 0 };
		expecteds = new int[] { 255, 255, 255 };
	}

	@Test
	public void shouldConvertRed() {
		cmyk = new double[] { 0, 1, 1, 0 };
		expecteds = new int[] { 255, 0, 0 };
	}

	@Test
	public void shouldConvertGreen() {
		cmyk = new double[] { 1, 0, 1, 0 };
		expecteds = new int[] { 0, 255, 0 };
	}

	@Test
	public void shouldConvertBlue() {
		cmyk = new double[] { 1, 1, 0, 0 };
		expecteds = new int[] { 0, 0, 255 };
	}

	@Test
	public void shouldConvertYellow() {
		cmyk = new double[] { 0, 0, 1, 0 };
		expecteds = new int[] { 255, 255, 0 };
	}

	@Test
	public void shouldConvertCyan() {
		cmyk = new double[] { 1, 0, 0, 0 };
		expecteds = new int[] { 0, 255, 255 };
	}

	@Test
	public void shouldConvertMagenta() {
		cmyk = new double[] { 0, 1, 0, 0 };
		expecteds = new int[] { 255, 0, 255 };
	}
}
