package velasco.karen.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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

public class PDFFile {

	private String documentName;
	private Document document = new Document();
	private PdfWriter writer;

	public PDFFile(String documentDir, String title) {// "HelloWorld.pdf"

		this.documentName = documentDir;

		try {// "HelloWorld.pdf"
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(documentDir + ".pdf"), document);

			document.open();
			document.add(new Paragraph(title));

			Rectangle margin = new Rectangle(20, 20, 580, 800);// 0
			margin.setBorder(Rectangle.BOX);
			margin.setBorderWidth(2);
			margin.setBorderColor(BaseColor.BLACK);

			document.add(margin);
			document.close();
			writer.close();

			System.out.println("PDF done");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
	}

	public PDFFile(String documentDir, String title, Integer questions) {// "HelloWorld.pdf"

		this.documentName = documentDir;

		try {// "HelloWorld.pdf"
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(documentDir + ".pdf"), document);

			document.open();
			document.add(new Paragraph(title));

			Rectangle margin = new Rectangle(20, 20, 580, 800);// 0
			margin.setBorder(Rectangle.BOX);
			margin.setBorderWidth(2);
			margin.setBorderColor(BaseColor.BLACK);

			document.add(margin);
			// QUESTIONS

			String dir = System.getProperty("user.dir");
			String inputDir = dir.concat("/src/images/Imagen1.png");

			String outputDir2 = dir.concat("/src/images/output2.png");

			document.close();
			writer.close();

			System.out.println("PDF done");

			int x = 25;
			int y = 750;
			/// 30
			for(int i=0; i<questions;i++) {
				
				this.addImage(x, y, i);
				y = y-30;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
	}

	public void addImage(Integer x, Integer y, Integer imageNumber) throws FileNotFoundException {

		try {
			// After made a pdf
			String dir = System.getProperty("user.dir");
			String src = dir.concat("/" + this.documentName + ".pdf");// +".pdf"

			String dest = dir.concat("/" + this.documentName + "_v2.pdf");//
			String img = dir.concat("/src/images/output2.png");// Imagen1

			String imgDir = dir.concat("/src/images/Imagen1.png");
			CustomImage customImage = new CustomImage(imgDir);
			customImage.setText(String.valueOf(imageNumber));
			customImage.writePNGimage(img);
			
			System.out.println(src);
			System.out.println(dest);
			System.out.println(img);

			File file = new File(dest);
			file.getParentFile().mkdirs();

			System.out.println("file created");

			PdfReader reader = new PdfReader(src);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

			// image
			Image image = Image.getInstance(img);
			PdfImage stream = new PdfImage(image, "", null);
			stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
			// write the steram
			PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
			image.setDirectReference(ref.getIndirectReference());
			image.setAbsolutePosition(x, y);

			PdfContentByte over = stamper.getOverContent(1);// page number
			over.addImage(image);

			stamper.close();
			reader.close();

			System.out.println("Before copy");

			this.copy(dest, src);
			// igualar el documento creado a la version 2 para guardar los cambios

			System.out.println("Image added.");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Add image error: " + e);
		}
	}

	public void copy(String src, String dest) {

		try {
			File file = new File(dest);
			file.getParentFile().mkdirs();

			PdfReader reader = new PdfReader(src);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

			PdfContentByte over = stamper.getOverContent(1);// page number

			stamper.close();
			reader.close();

			System.out.println("Copy finished.");

			File fileToDelete = new File(src);
			if (fileToDelete.delete()) {
				System.out.println("File deleted: " + src);
			} else {
				System.out.println("Delete option failed.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String documentName = "HelloWorld";

		/*
		 * PDFFile pdfFile = new PDFFile(documentName, "Examen 1"); if(pdfFile!=null) {
		 * pdfFile.addImage(25, 700); }
		 */ //// 25,700

		PDFFile pdfFile = new PDFFile(documentName, "Examen 1", 25);
		System.out.println("Finish.");
	}
}
