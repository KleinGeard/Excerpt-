package textextraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFTextExtractor implements ITextExtractor {

	private PDFTextStripper textStripper;
	private PDFParser parser;
	private COSDocument cosDoc = null;
	protected PDDocument pdDoc = null;
	
	public PDFTextExtractor() {
		this.initialiseTextStripper();
	}
	
	private void initialiseTextStripper() {
		
		try {
			this.textStripper = new PDFTextStripper();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public ArrayList<String> getText(File file) {
		
		this.initialisePDFBoxDocs(file);
		
		String textFromPDF = this.parse();
		this.closeDocs();
		
		return new ArrayList<String>(Arrays.asList(textFromPDF.split("\n")));
	}
	
	private void initialisePDFBoxDocs(File file) {
		
		try {
			
			this.parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
			this.parser.parse();
	   		this.cosDoc = this.parser.getDocument();
	   		this.pdDoc = new PDDocument(this.cosDoc);
	   		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String parse() {
		
		try {
			return this.textStripper.getText(this.pdDoc);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	private void closeDocs() {
		
		try {
   			
   			if (this.cosDoc != null)
                this.cosDoc.close();
   			if (this.pdDoc != null)
   				this.pdDoc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
