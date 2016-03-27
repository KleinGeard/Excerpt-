package textextraction;

import java.io.File;
import java.util.ArrayList;

public interface TextExtractor {

	public ArrayList<String> getText(File file);
	
}
