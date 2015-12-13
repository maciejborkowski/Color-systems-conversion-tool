package engine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Converter;

public class ConverterLuminanceChrominanceTest {
	int rgbIn[];
	int rgbOut[];
	double yuv[];
	double yiq[];
	
	@Test
	public void shouldConvertYuv() {
		rgbIn = new int[] { 255, 255, 255 };
		yuv = Converter.rgb2yuv(rgbIn[0], rgbIn[1], rgbIn[2]);
		rgbOut = Converter.yuv2rgb(yuv[0], yuv[1], yuv[2]);
		
		assertArrayEquals(rgbIn, rgbOut);
		
	}
	
	@Test
	public void shouldConvertYiq() {
		rgbIn = new int[] { 255, 255, 255 };
		yiq = Converter.rgb2yiq(rgbIn[0], rgbIn[1], rgbIn[2]);
		rgbOut = Converter.yuv2rgb(yiq[0], yiq[1], yiq[2]);
		
		assertArrayEquals(rgbIn, rgbOut);
	}

}
