package search;

import java.util.ArrayList;

import application.Colours;
import application.HTMLTextWrapper;

public class Excerpts {

	private String name;
	private String searchTerm;
	private ArrayList<Excerpt> excerpts;
	private int totalNumberOfMatches;
	private HTMLTextWrapper textColourer;
	
	public Excerpts(String name, String searchTerm) {
		
		this.name = name;
		this.searchTerm = searchTerm;
		this.excerpts = new ArrayList<>();
		this.totalNumberOfMatches = 0;
		this.textColourer = new HTMLTextWrapper();
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public int getNumberOfMatches() {
		
		return this.totalNumberOfMatches;	
		
	}
	
	public void addExcept(Excerpt excerpt) {
		
		this.excerpts.add(excerpt);
		this.totalNumberOfMatches += excerpt.getNumberOfMatches();
		
	}
	
	public ArrayList<String> getDisplayableArray() {
		
		ArrayList<String> arrayOfLinesInAllExcerpts = new ArrayList<>();
		
		arrayOfLinesInAllExcerpts = this.addHeader(arrayOfLinesInAllExcerpts);
		arrayOfLinesInAllExcerpts = this.addContents(arrayOfLinesInAllExcerpts);
		
		return arrayOfLinesInAllExcerpts;
		
	}
	
	private ArrayList<String> addHeader(ArrayList<String> arrayOfLinesInAllExcerpts) {
		
		arrayOfLinesInAllExcerpts.add(HTMLTextWrapper.html + 
				this.textColourer.wrapInColouredHTML(Colours.navy, "search term: " + this.searchTerm));
		arrayOfLinesInAllExcerpts.add(this.name);
		arrayOfLinesInAllExcerpts.add(HTMLTextWrapper.html + 
				this.textColourer.wrapInColouredHTML(Colours.navy, "matches in this file: " + this.totalNumberOfMatches));	
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
		
		for (int i = 0 ; i < numberOfNewLines ; i++) 
			arrayOfLinesInAllExcerpts.add("   ");
			
		return arrayOfLinesInAllExcerpts;
		
	}
	
	public boolean containsExcerpts() {
		
		return !this.excerpts.isEmpty();
		
	}
	
}
