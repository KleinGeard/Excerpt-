package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {
	
	private HashMap<String, ArrayList<String>> namesAndText;
	private JPanel panelCentre;
	private JTextField searchField;
	private String searchTerm;

	public SearchListener(HashMap<String, ArrayList<String>> namesAndText, JPanel panelCentre, JTextField searchField) {
		
		this.namesAndText = namesAndText;
		this.panelCentre = panelCentre;
		this.searchField = searchField;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.searchTerm = this.searchField.getText().toLowerCase();
		
		this.searchAll();
		
		this.searchField.setText("");
		
	}	
	
	public void searchAll() {
		
		this.panelCentre.removeAll();
		
		ResultsGetter resultsGetter = new ResultsGetter(this.namesAndText, this.searchTerm, this.panelCentre);
		ArrayList<Excerpts> results = resultsGetter.getResults();
		
		Displayer displayer = new Displayer(this.panelCentre, results);
		displayer.display();
		
	}

}
