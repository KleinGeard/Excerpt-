package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

import application.ComponentEnabler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {
	
	private HashMap<String, ArrayList<String>> namesAndText;
	private JPanel panelCentre;
	private JTextField searchField;
	private String searchTerm;
	private ComponentEnabler buttonEnabler;

	public SearchListener(HashMap<String, ArrayList<String>> namesAndText, JPanel panelCentre,
			JTextField searchField, ComponentEnabler buttonEnabler) {
		
		this.namesAndText = namesAndText;
		this.panelCentre = panelCentre;
		this.searchField = searchField;
		this.buttonEnabler = buttonEnabler;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.buttonEnabler.disableComponents();
		
		this.searchTerm = searchField.getText().toLowerCase();
		this.searchAll();
		
		this.searchField.setText("");
		this.buttonEnabler.enableComponents();
		this.searchField.requestFocus();
		
	}
	
	private void searchAll() {
		
		Thread t = new Thread(new Runnable() { //enables directoryLabel to change while files are loading
	        @Override
	        public void run() {
	        	
	        	panelCentre.removeAll();
	    		
	    		display(getResults());
	    		
	        }    
	        
	    });
		
		t.start();
		
	}
	
	private ArrayList<Excerpts> getResults() {
		
		Searcher searcher = new Searcher(this.namesAndText, this.searchTerm);
		return searcher.getResults();
		
	}
	
	private void display(ArrayList<Excerpts> results) {
		
		Displayer displayer = new Displayer(this.panelCentre, results, this.searchField);
		displayer.display();
		
	}

}
