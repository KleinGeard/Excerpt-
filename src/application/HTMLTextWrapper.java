package application;

public class HTMLTextWrapper {

	public HTMLTextWrapper() {

		
		
	}
	
	public String wrapInNavyHTML(String string) {
		
		return String.format("<font color = Navy>%s</font>", string);
		
	}

	public String wrapInWhiteHTML(String string) {
	
		return String.format("<font color = white>%s</font>", string);
	
	}

	public String wrapInRedHTML(String string) {
	
		return String.format("<font color = red>%s</font>", string);
	
	}

}
