package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import application.HTMLTextWrapper;

public class Searcher { //TODO maybe break up into smaller classes
	
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
		
		for (String name : this.namesAndText.keySet()) this.results.add(this.getExcerpts(name));
		
		this.display();
		
	}
	
	private void resetVariables() {
		
		this.panel_centre.removeAll();
		this.results.clear();
		
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
	
	private void addLine(String line, int indexesFromLastMatch, Excerpt excerpt) {
		
		
		
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
	
	private void display() {
		
		JPanel panel_centre_centre = this.createPanelCentreCentre();
		JScrollPane topScroller = this.createTopScroller(panel_centre_centre);
		
		this.panel_centre.add(topScroller, BorderLayout.NORTH);
		this.panel_centre.add(panel_centre_centre, BorderLayout.CENTER);
		
		this.panel_centre.revalidate();
		this.panel_centre.repaint();
		
	}
	
	private JPanel createPanelCentreCentre() {
		
		JPanel panel_centre_centre = new JPanel();
		panel_centre_centre.setLayout(new BorderLayout());
		panel_centre_centre.setBackground(Color.WHITE);
		panel_centre_centre.setSize(this.panel_centre.getSize());
		
		return panel_centre_centre;
		
	}
	
	private JScrollPane createTopScroller(JPanel panel_centre_centre) {
		
		JPanel panel_centre_scroller = this.getPanelCentreScroller(panel_centre_centre);
		
		JScrollPane topScroller = new JScrollPane(panel_centre_scroller);
		topScroller.getHorizontalScrollBar().setUnitIncrement(20);
		topScroller.setPreferredSize(new Dimension(this.panel_centre.getWidth(), 58));
		
		return topScroller;
		
	}
	
	private JPanel getPanelCentreScroller(JPanel panel_centre_centre) {
		
		JPanel panel_centre_scroller = new JPanel();
		panel_centre_scroller.setLayout(new FlowLayout());
		panel_centre_scroller.setBackground(Color.WHITE);
		this.addContentsToPanelCentreScroller(panel_centre_centre, panel_centre_scroller);
		
		return panel_centre_scroller;
		
	}
	
	private void addContentsToPanelCentreScroller(JPanel panel_centre_centre, JPanel panel_centre_scroller) {
		
		for (Excerpts excerpts : this.results) {
			
			if (excerpts.containsExcerpts()) {
				
				JButton nameButton = new JButton(excerpts.getName() + " / matches: " + excerpts.getNumberOfMatches());
				
				NameButtonListener nameButtonListener = new NameButtonListener(excerpts, panel_centre_centre);
				nameButton.addActionListener(nameButtonListener);
				
				panel_centre_scroller.add(nameButton);
				
			}
			
		}
		
	}

}
