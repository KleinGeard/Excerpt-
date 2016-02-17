package textextraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hwpf.extractor.Word6Extractor;

public class DOCTextExtractor {

	public DOCTextExtractor() {
		
	}
	
	public ArrayList<String> getTextFromDocFile(File file) {
		
		return new ArrayList<String>(Arrays.asList(this.parseToPlainText(file).split("\n")));
		
	}
	
	public String parseToPlainText(File file) {
		
		String text = "";
		
	    try {
	    	
			InputStream stream = new FileInputStream(file);
			Word6Extractor extractor = new Word6Extractor(stream);
			
			text = extractor.getText();
			
			extractor.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return text;
	    
	}

	
}
