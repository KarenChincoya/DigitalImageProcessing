package prueba1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ColoredToGrayScale {
	
	public static void main(String[] args) {
		BufferedImage img = null;
		File f = null; 

		String dir = System.getProperty("user.dir");
		String inputDir = dir.concat("/src/images/image1.jpg");
		String outputDir = dir.concat("/src/images/output1.jpg");
		
		//read image
		try {
			f = new File(inputDir);
			img = ImageIO.read(f);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		int width = img.getWidth();
		int height = img.getHeight();
		
		//convert to gray scale
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				int p = img.getRGB(x, y);
				
				int a = (p>>24)&0xff;
				int r = (p>>16)&0xff;
				int g = (p>>8)&0xff;
				int b = p&0xff; //bitwise ADD
				
				int avg = (r+g+b)/3;
				
				//replace RGB value with avg
				p = (a<<24) | (avg<<16) | (avg<<8) | avg;
				
				img.setRGB(x, y, p);
			}
		}
		
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
