package engine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import engine.Converter;

public class ConverterRgb2HexTest {
	int rgb[];
	String expected, actual;

	@After
	public void computeAndCheck() {
		actual = Converter.rgb2hex(rgb[0], rgb[1], rgb[2]);
		assertEquals(expected, actual.toUpperCase());
	}

	@Test
	public void shouldConvertBlack() {
		rgb = new int[] {0, 0, 0};
		expected = "#000000";
	}
	
	@Test
	public void shouldConvertWhite() {
		rgb = new int[] {255, 255, 255};
		expected = "#FFFFFF";
	}

	@Test
	public void shouldConvertRed() {
		rgb = new int[] {255, 0, 0};
		expected = "#FF0000";
	}
	
	@Test
	public void shouldConvertLime() {
		rgb = new int[] {0, 255, 0};
		expected = "#00FF00";
	}
	
	@Test
	public void shouldConvertBlue() {
		rgb = new int[] {0, 0, 255};
		expected = "#0000FF";
	}
	
	@Test
	public void shouldConvertYellow() {
		rgb = new int[] {255, 255, 0};
		expected = "#FFFF00";
	}
	
	@Test
	public void shouldConvertCyan() {
		rgb = new int[] {0, 255, 255};
		expected = "#00FFFF";
	}
	
	@Test
	public void shouldConvertMagenta() {
		rgb = new int[] { 255, 0, 255};
		expected = "#FF00FF";
	}
	
	@Test
	public void shouldConvertSilver() {
		rgb = new int[] {192, 192, 192};
		expected = "#C0C0C0";
	}
	
	@Test
	public void shouldConvertGray() {
		rgb = new int[] {128, 128, 128};
		expected = "#808080";
	}
	
	@Test
	public void shouldConvertMaroon() {
		rgb = new int[] {128, 0, 0};
		expected = "#800000";
	}
	
	@Test
	public void shouldConvertOlive() {
		rgb = new int[] {128, 128, 0};
		expected = "#808000";
	}
	
	@Test
	public void shouldConvertGreen() {
		rgb = new int[] {0, 128, 0};
		expected = "#008000";
	}
	
	@Test
	public void shouldConvertPurple() {
		rgb = new int[] {128, 0, 128};
		expected = "#800080";
	}
	
	@Test
	public void shouldConvertTeal() {
		rgb = new int[] {0, 128, 128};
		expected = "#008080";
	}
	
	@Test
	public void shouldConvertNavy() {
		rgb = new int[] {0, 0, 128};
		expected = "#000080";
	}
}
