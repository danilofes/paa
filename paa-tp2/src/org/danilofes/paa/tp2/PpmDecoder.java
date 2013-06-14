package org.danilofes.paa.tp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.danilofes.paa.tp2.RgbImage.Pixel;

/**
 * lê e escreve imagens no formato PPM.
 */
public class PpmDecoder {

	public RgbImage read(BufferedReader reader) throws IOException {
		Scanner scanner = new Scanner(reader);
		Pattern delimiterPattern = Pattern.compile("\\s+((#[^\\n]*)\\s*)*");
		scanner.useDelimiter(delimiterPattern);
		
		String magicString = scanner.next();
		if (!"P3".equals(magicString)) {
			throw new RuntimeException("Invalid format, P3 expected, found: " + magicString);
		}

		int width = scanner.nextInt();
		int height = scanner.nextInt();

		int maxColor = scanner.nextInt();

		int[][][] pixels = new int[height][width][3];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pixels[i][j][0] = scanner.nextInt();
				pixels[i][j][1] = scanner.nextInt();
				pixels[i][j][2] = scanner.nextInt();
			}
		}

		return new RgbImage(maxColor, pixels);
	}

	public void write(PrintStream writer, RgbImage image) throws IOException {
		writer.println("P3");
		
		int width = image.width;
		int height = image.height;
		int maxColor = image.maxColor;

		writer.println(width);
		writer.println(height);
		writer.println(maxColor);
		
		writer.println("# pixels");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Pixel p = image.pixelAt(i, j);
				//writer.printf("%3d %3d %3d ", p.r(), p.g(), p.b());
				writer.print(p.r());
				writer.print(' ');
				writer.print(p.g());
				writer.print(' ');
				writer.print(p.b());
				writer.print(' ');
			}
			writer.println();
		}
		writer.flush();
	}
}
