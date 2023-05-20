package com.feuji.weekendtrip.serviceImplementation;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Passenger;
import com.feuji.weekendtrip.model.Traveller;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfGeneratorForAdmin {

	List<Passenger> passengersList;
	List<Traveller> travellersList;

	public List<Passenger> getPassengersList() {
		return passengersList;
	}

	public void setPassengersList(List<Passenger> passengersList) {
		this.passengersList = passengersList;
	}

	public List<Traveller> getTravellersList() {
		return travellersList;
	}

	public void setTravellersList(List<Traveller> travellersList) {
		this.travellersList = travellersList;
	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=Mypdf.pdf");
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Rectangle rect = new Rectangle(577, 825, 18, 15); // you can resize rectangle
		rect.enableBorderSide(1);
		rect.enableBorderSide(2);
		rect.enableBorderSide(4);
		rect.enableBorderSide(8);
		rect.setBorderColor(BaseColor.BLACK);
		rect.setBorderWidth(1);
		document.add(rect);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(30);

		Paragraph paragraph = new Paragraph("Traveller's Details", fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		Image img = Image.getInstance(
				ClassLoader.getSystemResource("weekendtrips-low-resolution-logo-color-on-transparent-background.png"));

		img.scaleAbsolute(146, 70);

		Phrase phrase = new Phrase();
		phrase.add(new Chunk(img, 390, 0));
		document.add(new Paragraph(phrase));
		document.add(Chunk.NEWLINE);
		for (Traveller tList : travellersList) {
			document.add(new Paragraph("Traveller Id : " + tList.getTravellerId()));
			document.add(new Paragraph("Traveller Email : " + tList.getTravellerEmail()));
			
			List<Passenger> passengersList =tList.getPassenger();
			
			
			long menCount = passengersList.stream().filter((e) -> e.getPassengerGender().equalsIgnoreCase("male")).count();
			long womenCount = passengersList.stream().filter((e) -> e.getPassengerGender().equalsIgnoreCase("female")).count();
	        long childCount=passengersList.stream().filter((e)->e.getPassengerAge()<5).count();
			
	        
	        document.add(new Paragraph("List of Passengers -" + tList.getPassenger().size()+"                                                                    "+"Male -"+menCount+"   "+"Female-"+womenCount+"    Child-"+childCount));
	
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100f);
			table.setWidths(new int[] { 3, 3, 3});
			table.setSpacingBefore(3);

			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setPadding(3);
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setColor(CMYKColor.BLACK.darker());

			cell.setPhrase(new Phrase("Name", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Age", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Gender", font));
			table.addCell(cell);
			for (Passenger passenger : passengersList) {
				if (passenger.getTraveller().getTravellerId().equals(tList.getTravellerId())) {
					table.addCell(passenger.getPassengerName());
					table.addCell(String.valueOf(passenger.getPassengerAge()));
					table.addCell(passenger.getPassengerGender());
				}
			}
			document.add(table);
			document.add(new Paragraph(
					".............................................................................................................................."));

		}
		document.add(Chunk.NEWLINE);
		document.setPageCount(2);
		document.addAuthor("MAHESH");
		document.setRole(new PdfName("MY PDF"));

		document.close();
	}

////		 Chunk textAsChunk = new Chunk();
//	 textAsChunk.setBackground(new BaseColor(120, 200, 50));
//		public void watermark(File pdfFile, OutputStream output) throws IOException {
//		    try (final InputStream sourceStream = new FileInputStream(pdfFile);
//		         final PDDocument document = PDDocument.load(sourceStream)
//		    ) {
//		        for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
//		            PDPage currPage  = document.getPage(pageNumber);
//		            writeWatermarkWithTransparentImageOnPage(document, currPage);
//		        }
//		        document.save(output);
//		    }
//		}

//		private void writeWatermarkWithTransparentImageOnPage(PDDocument document, PDPage page)
//		        throws IOException {
//		    try (PDPageContentStream contentStream = new PDPageContentStream(
//		            document, page, PDPageContentStream.AppendMode.APPEND, true, true);
//		         InputStream watermarkFileStream = getWatermarkFileStream()
//		    ) {
//		        // Load watermark image
//		        BufferedImage image = ImageIO.read(watermarkFileStream);
//		        PDImageXObject pdxImage = LosslessFactory.createFromImage(document, image);
//
//		        // Set the opacity
//		        PDExtendedGraphicsState extendedGraphicsState = new PDExtendedGraphicsState();
//		        extendedGraphicsState.setNonStrokingAlphaConstant(0.35f);
//		        contentStream.setGraphicsStateParameters(extendedGraphicsState);
//
//		        // Center watermark image on page
//		        PDRectangle rect = page.getBBox();
//		        int imageX = Math.floorDiv((Math.round(rect.getWidth()) - pdxImage.getWidth()), 2);
//		        int imageY = Math.floorDiv((Math.round(rect.getHeight()) - pdxImage.getHeight()), 2);
//
//		        contentStream.drawImage(pdxImage, imageX, imageY);
//		    }
//		}
//
//		private InputStream getWatermarkFileStream() {
//		    try {
//		        Resource resource = new ClassPathResource(WATERMARK_RESOURCE_PATH);
//		        return resource.getInputStream();
//		    }
//		    catch (IOException e) {
//		        throw new RuntimeException(e);
//		    }
//		}
}

//		
//		for (Passengers passenger : listpassengers) {
//			
//			Paragraph lis = new Paragraph("Name: "+passenger.getPassengerName());
//			Paragraph lis1 = new Paragraph("Age: "+ passenger.getPassengerAge());
//			Paragraph lis2= new Paragraph("Gender: "+ passenger.getPassengerGender());
//			document.add(lis);
//			document.add(lis1);
//			document.add(lis2);
//		}
//		document.close();
//
//		PdfPTable table = new PdfPTable(4);
//		
//		table.setWidthPercentage(100f);
//		table.setWidths(new int[] { 3, 3, 3, 3 });
//		table.setSpacingBefore(4);
//		
//		PdfPCell cell = new PdfPCell();
//		cell.setBackgroundColor(CMYKColor.MAGENTA);
//		cell.setPadding(4);
//		
//		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//		font.setColor(CMYKColor.WHITE);
//		cell.setPhrase(new Phrase("Name", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Age", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Gender", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Aadhar", font));
//		table.addCell(cell);
//		for (Passenger passenger : listpassengers) {
//			table.addCell(passenger.getPassengerName());
//			table.addCell(String.valueOf(passenger.getPassengerAge()));
//			table.addCell(passenger.getPassengerGender());
//			table.addCell(String.valueOf(passenger.getPassengerAadhar()));
//		}
//		document.add(table);
//		document.close();
//	}

//	public void generate(HttpServletResponse response) throws DocumentException, IOException {
//
//		// Creating the Object of Document
//		Document document = new Document(PageSize.A4);
//
//		// Getting instance of PdfWriter
//		PdfWriter.getInstance(document, response.getOutputStream());
//
//		// Opening the created document to modify it
//		document.open();
//
//		// Creating font
//		// Setting font style and size
//		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//		fontTiltle.setSize(30);
//
//		// Creating paragraph
//		Paragraph paragraph = new Paragraph("Weekend Trips", fontTiltle);
//
//		// Aligning the paragraph in document
//		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//		// Adding the created paragraph in document
//		document.add(paragraph);
//
//		// Creating a table of 3 columns
//		PdfPTable table = new PdfPTable(4);
//
//		// Setting width of table, its columns and spacing
//		table.setWidthPercentage(100f);
//		table.setWidths(new int[] { 3, 3, 3, 3 });
//		table.setSpacingBefore(4);
//
//		// Create Table Cells for table header
//		PdfPCell cell = new PdfPCell();
//
//		// Setting the background color and padding
//		cell.setBackgroundColor(CMYKColor.MAGENTA);
//		cell.setPadding(4);
//
//		// Creating font
//		// Setting font style and size
//		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//		font.setColor(CMYKColor.WHITE);
//
//		// Adding headings in the created table cell/ header
//		// Adding Cell to table
//		cell.setPhrase(new Phrase("Name", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Age", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Gender", font));
//		table.addCell(cell);
//		cell.setPhrase(new Phrase("Aadhar", font));
//		table.addCell(cell);
//		// Iterating over the list of students
//		for (Passengers passenger : listpassengers) {
//			// Adding student id
//			table.addCell(passenger.getPassengerName());
//			// Adding student name
//			table.addCell(String.valueOf(passenger.getPassengerAge()));
//			// Adding student section
//			table.addCell(passenger.getPassengerGender());
//			table.addCell(passenger.getPassengerAadhar());
//		}
//		// Adding the created table to document
//		document.add(table);
//
//		// Closing the document
//		document.close();
//
//	}
