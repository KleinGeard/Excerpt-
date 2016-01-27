package search;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.HTMLTextWrapper;

public class Searcher {
	
	private HashMap<String, ArrayList<String>> namesAndText;
	private String searchTerm;
	private JPanel panel_centre;
	private ArrayList<Excerpts> results;
	private int excerptSize;
	private int lineIndex;
	private HTMLTextWrapper textColourer;

	public Searcher(HashMap<String, ArrayList<String>> namesAndText, String searchTerm, JPanel panel_centre) {
		
		this.namesAndText = namesAndText;
		this.searchTerm = searchTerm;
		this.panel_centre = panel_centre;
		this.results = new ArrayList<Excerpts>();
		this.excerptSize = 7;
		this.lineIndex = 0;
		this.textColourer = new HTMLTextWrapper();
		
	}
	
	public void searchAll() {
		
		this.resetVariables();
		
		for (String name : this.namesAndText.keySet()) {
			
			Excerpts excerpts = this.search(name);
			this.results.add(excerpts);
			
		}
		
		this.display();
		
	}
	
	private void resetVariables() {
		
		this.panel_centre.removeAll();
		this.results.clear();
		
	}
	
	private Excerpts search(String name) {
		
		ArrayList<String> lines = this.namesAndText.get(name);
		Excerpts fileExcerpts = new Excerpts(this.textColourer.wrapInNavyHTML(name));
		
		this.lineIndex = 0;
		
		while (this.lineIndex < lines.size() - 1) {
			
			String line = lines.get(this.lineIndex).toLowerCase();
			if (line.contains(this.searchTerm)) {
				
				Excerpt excerpt = this.addLinesToExcerpt(lines);
				fileExcerpts.addExcept(excerpt);
				
			}
			
			this.lineIndex++;
			
		}
		
		return fileExcerpts;
		
	}
	
	private Excerpt addLinesToExcerpt(ArrayList<String> lines) { //TODO break up into smaller methods
		
		Excerpt excerpt = new Excerpt();
		int indexesFromLastMatch = this.getOffset(this.lineIndex);
		int offSetFromLineIndex = this.getOffset(this.lineIndex);
		
		while (indexesFromLastMatch <= this.excerptSize) {
			
			int offsettedLineIndex = this.lineIndex + offSetFromLineIndex;
			
			if ((offsettedLineIndex) >= lines.size()) {
				break;
			}
			
			String line = lines.get(offsettedLineIndex);
			String lineToBeAdded = this.getLineNumber(offsettedLineIndex) + this.getSpaces(offsettedLineIndex) + this.getLineWithHighlightedText(line);
			
			if (line.contains(this.searchTerm)) {
				
				excerpt.addLine(lineToBeAdded, true);
				
				indexesFromLastMatch = 0;
				
			} else {
				
				excerpt.addLine(lineToBeAdded, false);
				
			}
			
			indexesFromLastMatch++;
			offSetFromLineIndex++;
			
		}
		
		this.lineIndex = this.lineIndex + offSetFromLineIndex;
		
		return excerpt;
		
	}
	
	private String getLineNumber(int lineIndex) {
		
		return "<html>" + this.textColourer.wrapInRedHTML(Integer.toString(lineIndex + 1));
		
	}
	
	private String getSpaces(int lineIndex) {
		
		int numberOfSpaces = 7 - ("" + (lineIndex + 1)).length();
		String spaces = "";
		
		for (int i = 0 ; i < numberOfSpaces ; i++) {
			spaces += "_";
		}
		
		return this.textColourer.wrapInWhiteHTML(spaces); //So that the underscores are invisible. JLists do not preserve whitespace.
		
	}
	
	private String getLineWithHighlightedText(String line) {
		
		return line.replace(this.searchTerm, this.textColourer.wrapInRedHTML(this.searchTerm));
		
	}
	
	private int getOffset(int lineIndex) {
		
		if (lineIndex <= this.excerptSize) { //ensures that offset is not greater than the lineIndex to avoid nullpointer
			return -lineIndex; 
		} else {
			return -this.excerptSize;
		}
		
	}
	
	private void display() {
		
		ArrayList<String> all = new ArrayList<>();
		
		for (Excerpts excerpts : this.results) {
			
			all.addAll(excerpts.getArrayOfLinesInAllExcerpts());
			
		}
		
		JList list = new JList(all.toArray());
		JScrollPane scroller = new JScrollPane(list);
		scroller.getVerticalScrollBar().setUnitIncrement(40);
		scroller.setPreferredSize(this.panel_centre.getSize());
		this.panel_centre.add(scroller, BorderLayout.CENTER);
		this.panel_centre.revalidate();
		
	}

}
