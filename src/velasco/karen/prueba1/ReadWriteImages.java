package velasco.karen.prueba1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ReadWriteImages {
	
	public static void main(String[] args) {
		//2350*1322
		int width = 2350;
		int height = 1322;
		
		BufferedImage image = null;
		String dir = System.getProperty("user.dir");
		String inputDir = dir.concat("/src/images/image1.jpg");
		String outputDir = dir.concat("/src/images/output1.jpg");
		//READ IMAGE
		try {

			File input_file = new File(inputDir);

			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			image = ImageIO.read(input_file);
			
			System.out.println("Reading complete.");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e);
		}
		
		//WRITE IMAGE
		
		try {
			File output_file = new File(outputDir);
			
			ImageIO.write(image, "jpg", output_file);
			
			System.out.println("Writing complete");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e);
		}
	}
}
