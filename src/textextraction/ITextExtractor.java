package textextraction;

import java.io.File;
import java.util.ArrayList;

public interface ITextExtractor {

	public ArrayList<String> getText(File file);
	
}
