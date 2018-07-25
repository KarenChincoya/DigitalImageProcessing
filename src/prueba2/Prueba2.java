package prueba2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

//https://howtodoinjava.com/apache-commons/create-pdf-files-in-java-itext-tutorial/

public class Prueba2 {
	public static void main(String[] args) throws IOException {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"), document);
			document.open();
			document.add(new Paragraph("A Hello World PDF document."));
			document.add(new Paragraph("Paragraph 2."));

			Rectangle prueba = new Rectangle(20, 20, 580, 800);//0
			prueba.setBorder(Rectangle.BOX);
			prueba.setBorderWidth(2);
			prueba.setBorderColor(BaseColor.BLACK);
			
			Rectangle rect = new Rectangle(400, 700);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(2);
			rect.setBorderColor(BaseColor.BLACK);
			
//			document.add((Element) image.getImage());

//			document.add(rect);
			document.add(prueba);
			document.close();
			writer.close();
			System.out.println("PDF done");
			
			//After made a pdf 
			String dir = System.getProperty("user.dir");
			String src = dir.concat("/HelloWorld.pdf");
			String dest = dir.concat("/HelloWorld1.pdf");
			String img = dir.concat("/src/images/output2.png");//Imagen1
			
			System.out.println(src);
			System.out.println(dest);
			System.out.println(img);
			
			File file = new File(dest);
			file.getParentFile().mkdirs();
			PdfReader reader = new PdfReader(src);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
			
			Image image = Image.getInstance(img);
			PdfImage stream = new PdfImage(image, "", null);
			stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
			
			PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
			image.setDirectReference(ref.getIndirectReference());
			image.setAbsolutePosition(25, 700);
			
			PdfContentByte over = stamper.getOverContent(1);//page number
			over.addImage(image);
			stamper.close();
			reader.close();
			
			System.out.println("End.");
			
		} catch (DocumentException e) {
			System.out.println("Document exception ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}
