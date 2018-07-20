package velasco.karen.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.itextpdf.awt.geom.Rectangle2D;

public class CustomImage {
	private File file = null;
	private BufferedImage image = null;
	private BufferedImage secondImage = null;
	private int width;
	private int height;

	private int p;

	public CustomImage() {

	}

	public CustomImage(String dir) {
		// read image
		try {
			file = new File(dir);// binaria
			image = ImageIO.read(file);

			this.width = image.getWidth();
			this.height = image.getHeight();

			System.out.println("Reading complete.");
			System.out.println("Width: " + width);
			System.out.println("Height: " + height);

			this.secondImage = image;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void generateRandomImage(int w, int h) {
		this.width = w;
		this.height = h;

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		System.out.println("Image width: " + image.getWidth());
		System.out.println("Image height: " + image.getHeight());

		// create random values pixel by pixel
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int a = (int) (Math.random() * 256);
				int r = (int) (Math.random() * 256);
				int g = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);

				int p = (a << 24) | (r << 16) | (g << 8) | b;// pixel

//				System.out.println("Pixel ("+x+","+y+"), r = "+r);
//				System.out.println("Pixel ("+x+","+y+"), g = "+g);
//				System.out.println("Pixel ("+x+","+y+"), b = "+b);

				this.image.setRGB(x, y, p);
			}
		}
		this.secondImage = image;
		System.out.println("Image created.");
	}

	public void writeJPGimage(String dir) {
		// write image
		try {
			if (secondImage != null) {
				File output_file = new File(dir);
				ImageIO.write(secondImage, "jpg", output_file);
				System.out.println("Writing complete.");
			} else {
				System.out.println("the image is null");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while writing jpg: " + e);
		}
	}

	public void writePNGimage(String dir) {
		// write image
		try {
			if (secondImage != null) {
				File output_file = new File(dir);
				ImageIO.write(secondImage, "png", output_file);
				System.out.println("Writing complete.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while writing: " + e);
		}
	}

	// 4bytes
	/*
	 * private void setARGBAtPixel(int x, int y) { this.p = image.getRGB(0, 0);
	 * this.alpha = (p >> 24) & 0xff; this.red = (p >> 16) & 0xff; this.green = (p
	 * >> 8) & 0xff; this.blue = p & 0xff;
	 * 
	 * }
	 */
	public void setPAtPixel(int x, int y, int a, int r, int g, int b) {
		p = (a << 24) | (r << 16) | (g << 8) | b;
		secondImage.setRGB(x, y, p);
	}

	public void toGrayScale() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				a = (p >> 24) & 0xff;
				r = (p >> 16) & 0xff;
				g = (p >> 8) & 0xff;
				b = p & 0xff; // bitwise ADD

				int avg = (r + g + b) / 3;

				// replace RGB value with avg
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;

				secondImage.setRGB(x, y, p);
			}
		}
		System.out.println("To gray scale completed.");
	}

	public void toNegative() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				// get a,r,g,b values

				a = (p >> 24) & 0xff;
				r = (p >> 16) & 0xff;
				g = (p >> 8) & 0xff;
				b = p & 0xff; // bitwise ADD

				r = 255 - r;
				g = 255 - g;
				b = 255 - b;

				// replace RGB value with avg
				p = (a << 24) | (r << 16) | (g << 8) | b;

				secondImage.setRGB(x, y, p);
			}
		}
		System.out.println("To negative completed.");
	}

	public void coloredToRed() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				// get a,r,g,b values

				a = (p >> 24) & 0xff;
				r = (p >> 16) & 0xff;
				// g = (p>>8)&0xff;
				// b = p&0xff; //bitwise ADD

				// r=0
				g = 0;
				b = 0;

				// replace RGB value with avg
				p = (a << 24) | (r << 16) | (g << 8) | b;

				secondImage.setRGB(x, y, p);
			}
		}
		System.out.println("Colored to red completed.");
	}

	public void coloredToGreen() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				// get a,r,g,b values

				a = (p >> 24) & 0xff;
				// r = (p>>16)&0xff;
				g = (p >> 8) & 0xff;
				// b = p&0xff; //bitwise ADD

				r = 0;
				// g = 0;
				b = 0;

				// replace RGB value with avg
				p = (a << 24) | (r << 16) | (g << 8) | b;

				secondImage.setRGB(x, y, p);
			}
		}
		System.out.println("Colored to green completed.");
	}

	public void coloredToBlue() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				// get a,r,g,b values

				a = (p >> 24) & 0xff;
				// r = (p>>16)&0xff;
				// g = (p>>8)&0xff;
				b = p & 0xff; // bitwise ADD

				r = 0;
				g = 0;
				// b = 0;

				// replace RGB value with avg
				p = (a << 24) | (r << 16) | (g << 8) | b;

				secondImage.setRGB(x, y, p);
			}
		}
		System.out.println("Colored to blue completed.");
	}

	public void toSepia() {

		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		int newRed;
		int newGreen;
		int newBlue;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				// get a,r,g,b values
				a = (p >> 24) & 0xff;
				r = (p >> 16) & 0xff;
				g = (p >> 8) & 0xff;
				b = p & 0xff; // bitwise ADD

				newRed = (int) (0.393 * r + 0.769 * g + 0.189 * b);
				newGreen = (int) (0.349 * r + 0.686 * g + 0.168 * b);
				newBlue = (int) (0.272 * r + 0.534 * g + 0.131 * b);

				r = (newRed > 255) ? 255 : newRed;
				g = (newGreen > 255) ? 255 : newGreen;
				b = (newBlue > 255) ? 255 : newBlue;

				// replace RGB value with values
				p = (a << 24) | (r << 16) | (g << 8) | b;

				secondImage.setRGB(x, y, p);
			}
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setText(String string) {
		secondImage = this.image;
		Graphics graphics = this.secondImage.getGraphics();
		// Graphics graphics = bufferedImage.getGraphics();
		// graphics.setColor(Color.LIGHT_GRAY);
		graphics.setColor(Color.white);
//		graphics.fill
		graphics.fillRect(0, 0, 40, 24);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial Black", Font.BOLD, 11));
		graphics.drawString(string, 5, 15);
	}

	public void toBlackAndWhite() {
		this.secondImage = image;

		int p;
		int a;
		int r;
		int g;
		int b;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				p = secondImage.getRGB(x, y);

				a = (p >> 24) & 0xff;
				r = (p >> 16) & 0xff;
				g = (p >> 8) & 0xff;
				b = p & 0xff; // bitwise ADD

				int avg = (r + g + b) / 3;
				if (avg > 128) {
					// white
					r = 255;
					g = 255;
					b = 255;

				} else {
					// black
					r = 0;
					g = 0;
					b = 0;
				}

				// replace RGB value with avg
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;

				secondImage.setRGB(x, y, p);
			}
		}
	}

	public void generateBlackImage(int w, int h) {
		this.width = w;
		this.height = h;

		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		System.out.println("Image width: " + image.getWidth());
		System.out.println("Image height: " + image.getHeight());

		// create random values pixel by pixel
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int a = 255;
				int r = 0;
				int g = 0;
				int b = 0;

				int p = (a << 24) | (r << 16) | (g << 8) | b;// pixel

//				System.out.println("Pixel ("+x+","+y+"), r = "+r);
//				System.out.println("Pixel ("+x+","+y+"), g = "+g);
//				System.out.println("Pixel ("+x+","+y+"), b = "+b);

				this.image.setRGB(x, y, p);
			}
		}
		this.secondImage = image;
		System.out.println("Image created.");
	}

	public Respuesta getRespuesta(int limInfX, int limSupX, int y) {

		this.secondImage = image;

		System.out.println("Contar pixeles");
		// Porque pasan 260,928
		int dif = limSupX - limInfX;
		int cociente = dif / 4;// porque tenemos 4 respuestas

		int limInf = limInfX;
		int limSup = limInf + cociente;

		int[] limInfSeccion = new int[4];
		int[] limSupSeccion = new int[4];

		for (int i = 0; i < 4; i++) {

			System.out.println("[" + limInf + "," + limSup + "]");
			limInfSeccion[i] = limInf;
			limSupSeccion[i] = limSup - 1;

			limInf = limSup;
			limSup = limInf + cociente;
		}

		// CUENTAS
		int sumatoria[] = { 0, 0, 0, 0 };

		// A = 0
		// B = 1
		// C = 2
		// D = 3

		for (int pX = limInfX; pX < limSupX; pX++) {
			// is black
			int p = secondImage.getRGB(pX, y);

			int a = (p >> 24) & 0xff;
			int r = (p >> 16) & 0xff;
			int g = (p >> 8) & 0xff;
			int b = p & 0xff; // bitwise ADD

			boolean isBlack = this.isBlack(r, g, b);

			if (pX >= limInfSeccion[0] && pX < limSupSeccion[0] && isBlack == true) {
				sumatoria[0]++;
			}

			if (pX >= limInfSeccion[1] && pX < limSupSeccion[1] && isBlack == true) {
				sumatoria[1]++;
			}

			if (pX >= limInfSeccion[2] && pX < limSupSeccion[2] && isBlack == true) {
				sumatoria[2]++;
			}

			if (pX >= limInfSeccion[3] && pX < limSupSeccion[3] && isBlack == true) {
				sumatoria[3]++;
			}

			/*
			 * a = (p >> 24) & 0xff;
			 */

			// una vez analizado, trazar la linea roja
/*			int pixel = this.secondImage.getRGB(pX, y);
			int alpha = 255;
			int red = 0;
			int green = 0;
			int blue = 0;

			pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;// pixel

			this.secondImage.setRGB(pX, y, pixel);
			this.secondImage.setRGB(pX, y + 1, pixel);
			this.secondImage.setRGB(pX, y + 2, pixel);
			this.secondImage.setRGB(pX, y + 3, pixel);
			this.secondImage.setRGB(pX, y + 4, pixel);
			this.secondImage.setRGB(pX, y + 5, pixel);
			this.secondImage.setRGB(pX, y + 6, pixel);
			this.secondImage.setRGB(pX, y + 7, pixel);
			this.secondImage.setRGB(pX, y + 8, pixel);
			this.secondImage.setRGB(pX, y + 9, pixel);
			this.secondImage.setRGB(pX, y + 10, pixel);*/

		}

		this.image = this.secondImage;

		System.out.println("A: " + sumatoria[0]);
		System.out.println("B: " + sumatoria[1]);
		System.out.println("C: " + sumatoria[2]);
		System.out.println("D: " + sumatoria[3]);

		int cont = 0;
		
		for(int i=0; i<4;i++) {
			if(sumatoria[i]>0) cont++; //cuenta los NO Nulos
		}
		
		if(cont==0)return Respuesta.empty;
		
		if(cont>1) return Respuesta.moreThan1;
		
		// encontrar el mayor
		int max = sumatoria[0];
		int indice = 0;

		for (int i = 1; i < 4; i++) {
			if (sumatoria[i] > max) {
				max = sumatoria[i];
				indice = i;
			}
		}

		switch (indice) {
		case 0:
			return Respuesta.A;
		case 1:
			return Respuesta.B;
		case 2:
			return Respuesta.C;
		case 3:
			return Respuesta.D;
		default:
			return Respuesta.idk;
		}
	}

	public boolean isBlack(int r, int g, int b) {
		if (r == 0 && g == 0 && b == 0)
			return true;
		return false;
	}

	public void setLine(int x1, int x2, int y) {

		int pixel = secondImage.getRGB(x1, y);
		int alpha = 50 & 0xff;// 255
		int red = 255;
		int green = 255;
		int blue = 255;

		this.secondImage = this.image;

		System.out.println("rango: [" + x1 + "," + x2 + "]");

		for (int x = x1; x < x2; x++) {

			pixel = secondImage.getRGB(x, y);
			alpha = 100;// 255 (p >> 24) & 0xff
			red = 255;
			green = 0;
			blue = 0;

			pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;// pixel

			for (int i = 0; i < 5; i++) {
				this.secondImage.setRGB(x, y + i, pixel);
			}
		}
//		this.image = this.secondImage;
		System.out.println("Set line completed.");
	}

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		
		String inputDir = dir.concat("/src/images/e4.jpg");

		String outputDir1 = dir.concat("/src/images/result1.png");
		String outputDir2 = dir.concat("/src/images/output1.png");
		String outputDir3 = dir.concat("/src/images/output3.png");

/*		int width = 640;
		int height = 320;
*/
		CustomImage img = new CustomImage(inputDir);
		img.toGrayScale();
		img.writePNGimage(outputDir1);
		
		CustomImage img1 = new CustomImage(outputDir1);
		img1.toBlackAndWhite();
		img1.writePNGimage(outputDir1);

		int limInfX = 260;
		int limSupX = 930;

		int y = 540;

		Respuesta[] respuesta = new Respuesta[5];

		CustomImage imgR = new CustomImage(outputDir1);// es el resultado

		int x1 = 135;
		int x2 = 465;

		//168 -230
		int y1 = 168;
		
		respuesta[0] = imgR.getRespuesta(x1, x2, y1);
		respuesta[1] = imgR.getRespuesta(x1, x2, y1+60);
		respuesta[2] = imgR.getRespuesta(x1, x2, y1+60+60);
		respuesta[3] = imgR.getRespuesta(x1, x2, y1+60+60+60);
		respuesta[4] = imgR.getRespuesta(x1, x2, y1+60+60+60+60);

		System.out.println("Respuestas: ");

		for (int i = 0; i < 5; i++) {
			System.out.println("\t" + respuesta[i]);
		}

		imgR.setLine(x1, x2, y1);
		imgR.setLine(x1, x2, y1+60);
		imgR.setLine(x1, x2, y1+60+60);
		imgR.setLine(x1, x2, y1+60+60+60);
		imgR.setLine(x1, x2, y1+60+60+60+60);
		imgR.writeJPGimage(outputDir1);

		System.out.println("Finish.");
	}
}
