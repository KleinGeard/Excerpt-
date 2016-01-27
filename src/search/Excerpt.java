package search;

import java.util.ArrayList;

public class Excerpt {

	private ArrayList<String> lines;
	private int numberOfMatches;
	
	public Excerpt() {
		
		this.lines = new ArrayList<>();
		this.numberOfMatches = 0;
		
	}

	public void addLine(String line, boolean hasMatch) {
		
		this.lines.add(line);
		
		if (hasMatch) {
			this.numberOfMatches++;
		}
		
	}
	
	public void print() {
		
		
		System.out.println(this.toString());
		
		
	}
	
	public String getText() {
		
		String text = "";
		
		for (String line : this.lines) {
			text += line + "\n";
		}
		
		return text;
		
	}
	
	public ArrayList<String> getArray() {
		
		return this.lines;
		
	}
	
	public int getNumberOfMatches() {
		
		return this.numberOfMatches;
		
	}
	
	@Override
	public String toString() {
		
		String toString = "";
		
		for (String line : this.lines) {
			toString += line + "\n";
		}
		
		return toString;
		
	}
	
}