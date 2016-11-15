package textextraction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLTextExtractor implements TextExtractor {

	@Override
	public ArrayList<String> getText(File file) {
		
		String text = "";
		
		try {
			Document doc = Jsoup.parse(file, "UTF-8");
			text = doc.body().text();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return new ArrayList<String> (Arrays.asList(text.split("\n")));
	}

}
