package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtil {
	
	// This class will contain methods to handle PDF files
	
	// This method will read the PDF file and return the text content as a String
	public static String readPDF(String fileName) {
		String text = "";
		
		try {
			//Read the PDF file
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Files\\"+fileName);
			
			// Load PDF files into a PDF reader classes
			PDDocument pdfDocument =PDDocument.load(fis);
			
			// Extract complete text from the PDF document (From 1st page to last page
			PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(pdfDocument);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		return text;
	}
	
	// This method will read the PDF file and return the text content as a String
		public static String readPDF(String fileName,int startPage, int endPage) {
			String text = "";
			
			try {
				//Read the PDF file
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Files\\"+fileName);
				
				// Load PDF files into a PDF reader classes
				PDDocument pdfDocument =PDDocument.load(fis);
				
				// Extract complete text from the PDF document (From 1st page to last page
				PDFTextStripper pdfStripper = new PDFTextStripper();
				
				// Set the start and end page for extraction
				pdfStripper.setStartPage(startPage);
				pdfStripper.setEndPage(endPage);
				
				// Extract text from the specified pages
				text = pdfStripper.getText(pdfDocument);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 	
			return text;
		}
	
	
}
