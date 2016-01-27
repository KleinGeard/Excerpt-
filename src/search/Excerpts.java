package search;

import java.util.ArrayList;

public class Excerpts {

	private String name;
	private ArrayList<Excerpt> excerpts;
	private int totalNumberOfMatches;
	
	public Excerpts(String name) {
		
		this.name = name;
		this.excerpts = new ArrayList<>();
		this.totalNumberOfMatches = 0;
		
	}
	
	@Override
	public String toString() {
		
		String text = "";
		text += this.name + "\n";
		text = this.addSpaces(3, text);
		
		for (Excerpt excerpt : this.excerpts) {
			
			text += excerpt.getText();
			text = this.addSpaces(3, text);
			
		}
		
		return text;
		
	}
	
	public ArrayList<String> getArrayOfLinesInAllExcerpts() {
		
		ArrayList<String> arrayOfLinesInAllExcerpts = new ArrayList<>();
		
		arrayOfLinesInAllExcerpts.add(this.name);
		arrayOfLinesInAllExcerpts.add(String.format("<html><font color = navy>Matches in this file: %s</font>", this.totalNumberOfMatches));
		
		arrayOfLinesInAllExcerpts = this.addLines(3, arrayOfLinesInAllExcerpts);
		
		for (Excerpt excerpt : this.excerpts) {
			
			arrayOfLinesInAllExcerpts.addAll(excerpt.getArray());
			arrayOfLinesInAllExcerpts = this.addLines(3, arrayOfLinesInAllExcerpts);
			
		}
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	private ArrayList<String> addLines(int numberOfNewLines, ArrayList<String> all) {
		
		for (int i = 0 ; i < numberOfNewLines ; i++) {
			
			all.add("   ");
			
		}
		
		return all;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public void addExcept(Excerpt excerpt) {
		
		this.excerpts.add(excerpt);
		this.totalNumberOfMatches += excerpt.getNumberOfMatches();
		
	}
	
	public void print() {
		
		System.out.println(this.toString());
		
	}
	
	private String addSpaces(int numberOfSpaces, String text) {
		
		for (int i = 0 ; i < numberOfSpaces ; i++) {
			
			text += "\n";
			
		}
		
		return text;
		
	}
	
	
	
}
