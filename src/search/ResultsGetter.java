package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import application.HTMLTextWrapper;

public class ResultsGetter {

	private HashMap<String, ArrayList<String>> namesAndText;
	private String searchTerm;
	private ArrayList<Excerpts> results;
	private int excerptSize;
	private int lineIndex;
	private HTMLTextWrapper textColourer;
	
	public ResultsGetter(HashMap<String, ArrayList<String>> namesAndText, String searchTerm) {

		this.namesAndText = namesAndText;
		this.searchTerm = searchTerm;
		this.results = new ArrayList<Excerpts>();
		this.excerptSize = 7;
		this.lineIndex = 0;
		this.textColourer = new HTMLTextWrapper();
		
	}
	
	public ArrayList<Excerpts> getResults() {
		
		for (String name : this.namesAndText.keySet()) this.results.add(this.getExcerpts(name));
		
		return this.results;
		
	}

	private Excerpts getExcerpts(String name) {
		
		ArrayList<String> lines = this.namesAndText.get(name);
		Excerpts excerpts = new Excerpts("<html>" + this.textColourer.wrapInNavyHTML(name));
		
		this.lineIndex = 0;
		
		while (this.lineIndex < lines.size() - 1) {
			
			String line = lines.get(this.lineIndex).toLowerCase();
			
			if (line.contains(this.searchTerm)) excerpts.addExcept(this.getExcerpt(lines));
			
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
			if (containsSearchTerm) indexesFromLastMatch = 0;
			
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
		
		return "<html>" + this.textColourer.wrapInRedHTML(Integer.toString(lineIndex + 1));
		
	}
	
	private String getSpaces(int lineIndex) {
		
		int numberOfSpaces = 7 - ("" + (lineIndex + 1)).length();
		String spaces = "";
		
		for (int i = 0 ; i < numberOfSpaces ; i++) spaces += "_";
		
		return this.textColourer.wrapInWhiteHTML(spaces); //So that the underscores are invisible. JLists do not preserve whitespace.
		
	}
	
	private String getLineWithHighlightedSearchTerm(String line) {
		
		return line.replace(this.searchTerm, this.textColourer.wrapInRedHTML(this.searchTerm));
		
	}
	
}
