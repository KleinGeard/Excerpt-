package utilities;

public class HTMLTextWrapper {
	
	public static final String html = "<html>";
	
	public String wrapInColouredHTML(String colour, String string) {
		
		return String.format("<font color = %s>%s</font>", colour, string);
		
	}

}
