package prueba1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GetSetPixels {

	public static void main(String[] args) {
		BufferedImage img = null;
		File f = null; 
		
		String dir = System.getProperty("user.dir");
		String inputDir = dir.concat("/src/images/image1.jpg");
		String outputDir = dir.concat("/src/images/output1.jpg");
		
		try {
			f = new File(inputDir);
			img = ImageIO.read(f);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		int p = img.getRGB(0, 0);
		
		/*
		 * To get the bits right the 32 bits & bitwise ADD 0xff
		 * hexadecimal
		 */
		
		int a = (p>>24) & 0xff;
		int r = (p>>16) & 0xff;
		int g = (p>>8) & 0xff;
		int b = p & 0xff;
		
		//for simplicity ( example ) 
		a = 255;
		r = 100;
		g = 150;
		b = 200;
		
		//set the pixel value
		p = (a<<24) | (r<<16) | (g<<8) | b;
		img.setRGB(0, 0, p);
		//p es rgb
		
		//write image
		try {
			f = new File(outputDir);
			ImageIO.write(img, "jpg", f);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
