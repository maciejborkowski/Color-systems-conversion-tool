package engine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import engine.Converter;

public class ConverterHex2RgbTest {
	String hex;
	int expecteds[], actuals[];

	@After
	public void computeAndCheck() {
		actuals = Converter.hex2rgb(hex);
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void shouldConvertBlack() {
		hex = "#000000";
		expecteds = new int[] { 0, 0, 0 };
	}

	@Test
	public void shouldConvertWhite() {
		hex = "#FFFFFF";
		expecteds = new int[] { 255, 255, 255 };
	}

	@Test
	public void shouldConvertRed() {
		hex = "#FF0000";
		expecteds = new int[] { 255, 0, 0 };
	}

	@Test
	public void shouldConvertLime() {
		hex = "#00FF00";
		expecteds = new int[] { 0, 255, 0 };
	}

	@Test
	public void shouldConvertBlue() {
		hex = "#0000FF";
		expecteds = new int[] { 0, 0, 255 };
	}

	@Test
	public void shouldConvertYellow() {
		hex = "#FFFF00";
		expecteds = new int[] { 255, 255, 0 };
	}

	@Test
	public void shouldConvertCyan() {
		hex = "#00FFFF";
		expecteds = new int[] { 0, 255, 255 };
	}

	@Test
	public void shouldConvertMagenta() {
		hex = "#FF00FF";
		expecteds = new int[] { 255, 0, 255 };
	}

	@Test
	public void shouldConvertSilver() {
		hex = "#C0C0C0";
		expecteds = new int[] { 192, 192, 192 };
	}

	@Test
	public void shouldConvertGray() {
		hex = "#808080";
		expecteds = new int[] { 128, 128, 128 };
	}

	@Test
	public void shouldConvertMaroon() {
		hex = "#800000";
		expecteds = new int[] { 128, 0, 0 };
	}

	@Test
	public void shouldConvertOlive() {
		hex = "#808000";
		expecteds = new int[] { 128, 128, 0 };
	}

	@Test
	public void shouldConvertGreen() {
		hex = "#008000";
		expecteds = new int[] { 0, 128, 0 };
	}

	@Test
	public void shouldConvertPurple() {
		hex = "#800080";
		expecteds = new int[] { 128, 0, 128 };
	}

	@Test
	public void shouldConvertTeal() {
		hex = "#008080";
		expecteds = new int[] { 0, 128, 128 };
	}

	@Test
	public void shouldConvertNavy() {
		hex = "#000080";
		expecteds = new int[] { 0, 0, 128 };
	}
}
