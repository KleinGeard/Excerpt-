package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {
	
	private HashMap<String, ArrayList<String>> namesAndText;
	private JPanel panel_centre;
	private JTextField searchField;
	private String searchTerm;

	public SearchListener(HashMap<String, ArrayList<String>> namesAndText, JPanel panel_centre, JTextField searchField) {
		
		this.namesAndText = namesAndText;
		this.panel_centre = panel_centre;
		this.searchField = searchField;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.searchTerm = this.searchField.getText().toLowerCase();
		
		Searcher searcher = new Searcher(namesAndText, searchTerm, this.panel_centre);
		searcher.searchAll();
		
		this.searchField.setText("");
		
	}	

}
