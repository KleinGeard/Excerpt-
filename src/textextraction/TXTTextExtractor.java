package textextraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTTextExtractor implements TextExtractor {

	private BufferedReader reader;

	@Override
	public ArrayList<String> getText(File file) {
		
		ArrayList<String> lines = new ArrayList<>();
		this.addLines(file, lines);
		
		return lines;
	}
	
	private void addLines(File file, ArrayList<String> lines) {
		
		String line;
		
		try {
			this.reader = new BufferedReader(new FileReader(file));
			
			while((line = this.reader.readLine()) != null) 
				lines.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}		
			
		
	}
	
}
