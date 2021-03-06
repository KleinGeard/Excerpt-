package textextraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DOCXTextExtractor implements TextExtractor {

	public ArrayList<String> getText(File file) {

		String text = "";
		
		try {
			
			XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
			XWPFWordExtractor we = new XWPFWordExtractor(docx);
			text = we.getText();
			we.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String> (Arrays.asList(text.split("\n")));
	}
	
}
