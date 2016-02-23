package search;

import java.util.ArrayList;
import java.util.HashMap;

import utilities.Colours;
import utilities.HTMLTextWrapper;

public class Searcher {

	private HashMap<String, ArrayList<String>> namesAndText;
	private String searchTerm;
	private ArrayList<Excerpts> results;
	private HTMLTextWrapper textColourer;
	private int excerptSize;
	private int lineIndex;
	
	public Searcher(HashMap<String, ArrayList<String>> namesAndText, String searchTerm) {

		this.namesAndText = namesAndText;
		this.searchTerm = searchTerm;
		this.results = new ArrayList<Excerpts>();
		this.textColourer = new HTMLTextWrapper();
		this.excerptSize = 7;
		this.lineIndex = 0;
		
	}
	
	public ArrayList<Excerpts> getResults() {
		
		for (String name : this.namesAndText.keySet()) 
			this.results.add(this.getExcerpts(name));
		
		return this.results;
		
	}

	private Excerpts getExcerpts(String name) {
		
		ArrayList<String> lines = this.namesAndText.get(name);
		Excerpts excerpts = new Excerpts(HTMLTextWrapper.html + this.textColourer.wrapInColouredHTML(Colours.navy, name), this.searchTerm);
		
		this.lineIndex = 0;
		
		while (this.lineIndex < lines.size() - 1) {
			
			String line = lines.get(this.lineIndex).toLowerCase();
			
			if (line.contains(this.searchTerm)) 
				excerpts.addExcept(this.getExcerpt(lines));
			
			this.lineIndex++;
			
		}
		
		return excerpts;
		
	}

	private Excerpt getExcerpt(ArrayList<String> lines) {
		
		Excerpt excerpt = new Excerpt();
		this.addLinesToExcerpt(excerpt, lines);
		
		return excerpt;
		
	}
	
	private void addLinesToExcerpt(Excerpt excerpt, ArrayList<String> lines) {
		
		int indexesFromLastMatch = this.getOffset(this.lineIndex);
		this.lineIndex = this.lineIndex + indexesFromLastMatch;
		
		while (indexesFromLastMatch <= this.excerptSize && this.lineIndex < lines.size()) {
			
			String line = lines.get(this.lineIndex).toLowerCase();
			boolean containsSearchTerm = line.contains(this.searchTerm);
			
			excerpt.addLine(this.getLineToBeAdded(line), containsSearchTerm);
			if (containsSearchTerm) 
				indexesFromLastMatch = 0;
			
			indexesFromLastMatch++;
			this.lineIndex++;
			
		}
		
	}
	
	private int getOffset(int lineIndex) {
		
		return (lineIndex <= this.excerptSize) ? -lineIndex : -this.excerptSize;
		
	}
	
	private String getLineToBeAdded(String line) {
		
		return this.getLineNumber(this.lineIndex) + this.getSpaces(this.lineIndex) + this.getLineWithHighlightedSearchTerm(line);
		
	}
	
	private String getLineNumber(int lineIndex) {
		
		return HTMLTextWrapper.html + this.textColourer.wrapInColouredHTML(Colours.red, Integer.toString(lineIndex + 1));
		
	}
	
	private String getSpaces(int lineIndex) {
		
		String spaces = "";
		int numberOfSpaces = 7 - ("" + (lineIndex + 1)).length();
		
		for (int i = 0 ; i < numberOfSpaces ; i++) 
			spaces += " "; //this character is [U-2000] it is NOT a regular space
		
		return spaces;
	}
	
	private String getLineWithHighlightedSearchTerm(String line) {
		
		return line.replace(this.searchTerm, this.textColourer.wrapInColouredHTML(Colours.red, this.searchTerm));
		
	}
	
}
