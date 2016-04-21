package utilities;

public final class HTMLTextWrapper {
	
	public static final String html = "<html>";
	
	public static String wrapInColouredHTML(String colour, String string) {
		
		return String.format("<font color = %s>%s</font>", colour, string);
		
	}

}
