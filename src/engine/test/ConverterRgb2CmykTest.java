package engine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Converter;

public class ConverterRgb2CmykTest {
	int rgb[];
	double expecteds[], actuals[];
	double delta = 0.01;

	@Test
	public void shouldConvertBlack() {
		rgb = new int[]{0, 0, 0};
		expecteds = new double[]{0, 0, 0, 1};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertWhite() {
		rgb = new int[]{255, 255, 255};
		expecteds = new double[]{0, 0, 0, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}

	@Test
	public void shouldConvertRed() {
		rgb = new int[]{255, 0, 0};
		expecteds = new double[]{0, 1, 1, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertGreen() {
		rgb = new int[]{0, 255, 0};
		expecteds = new double[]{1, 0, 1, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertBlue() {
		rgb = new int[]{0, 0, 255};
		expecteds = new double[]{1, 1, 0, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertYellow() {
		rgb = new int[]{255, 255, 0};
		expecteds = new double[]{0, 0, 1, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertCyan() {
		rgb = new int[]{0, 255, 255};
		expecteds = new double[]{1, 0, 0, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
	
	@Test
	public void shouldConvertMagenta() {
		rgb = new int[]{255, 0, 255};
		expecteds = new double[]{0, 1, 0, 0};
		actuals = Converter.rgb2cmyk(rgb[0], rgb[1], rgb[2]);
		assertArrayEquals(expecteds, actuals, delta);
	}
}
