package search;

import java.util.ArrayList;

import application.HTMLTextWrapper;

public class Excerpts {

	private String name;
	private ArrayList<Excerpt> excerpts;
	private int totalNumberOfMatches;
	private HTMLTextWrapper textColourer;
	
	public Excerpts(String name) {
		
		this.name = name;
		this.excerpts = new ArrayList<>();
		this.totalNumberOfMatches = 0;
		this.textColourer = new HTMLTextWrapper();
		
	}
	
	public void addExcept(Excerpt excerpt) {
		
		this.excerpts.add(excerpt);
		this.totalNumberOfMatches += excerpt.getNumberOfMatches();
		
	}
	
	@Override
	public String toString() {
		
		String text = "";
		
		for (String line : this.getArrayOfLinesInAllExcerpts()) {
			
			text += line + "\n";
			
		}
		
		return text;
		
	}
	
	public ArrayList<String> getArrayOfLinesInAllExcerpts() { //the lines that displayed on the screen using JList	
															  //TODO think of a better name
		ArrayList<String> arrayOfLinesInAllExcerpts = new ArrayList<>();
		
		arrayOfLinesInAllExcerpts = this.addHeader(arrayOfLinesInAllExcerpts);
		arrayOfLinesInAllExcerpts = this.addContents(arrayOfLinesInAllExcerpts);
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	private ArrayList<String> addHeader(ArrayList<String> arrayOfLinesInAllExcerpts) {
		
		arrayOfLinesInAllExcerpts.add(this.name);
		arrayOfLinesInAllExcerpts.add("<html>" + this.textColourer.wrapInNavyHTML("matches in this file: " + this.totalNumberOfMatches));	
		arrayOfLinesInAllExcerpts = this.addLines(3, arrayOfLinesInAllExcerpts);
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	private ArrayList<String> addContents(ArrayList<String> arrayOfLinesInAllExcerpts) {
		
		for (Excerpt excerpt : this.excerpts) {
			
			arrayOfLinesInAllExcerpts.addAll(excerpt.getArray());
			arrayOfLinesInAllExcerpts = this.addLines(3, arrayOfLinesInAllExcerpts);
			
		}
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	private ArrayList<String> addLines(int numberOfNewLines, ArrayList<String> arrayOfLinesInAllExcerpts) {
		
		for (int i = 0 ; i < numberOfNewLines ; i++) {
			
			arrayOfLinesInAllExcerpts.add("   ");
			
		}
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	public boolean containsExcerpts() {
		
		return !this.excerpts.isEmpty();
		
	}
	
}
