package application;

import java.awt.Color;

public class HTMLTextWrapper {
	
	public static final String html = "<html>";
	
	public String wrapInColouredHTML(String colour, String string) {
		
		return String.format("<font color = %s>%s</font>", colour, string);
		
	}

}
