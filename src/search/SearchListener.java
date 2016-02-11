package search;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

import application.ButtonEnabler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchListener implements ActionListener {
	
	private HashMap<String, ArrayList<String>> namesAndText;
	private JPanel panelCentre;
	private JTextField searchField;
	private String searchTerm;
	private ButtonEnabler buttonEnabler;

	public SearchListener(HashMap<String, ArrayList<String>> namesAndText, JPanel panelCentre,
			JTextField searchField, ButtonEnabler buttonEnabler) {
		
		this.namesAndText = namesAndText;
		this.panelCentre = panelCentre;
		this.searchField = searchField;
		this.buttonEnabler = buttonEnabler;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Thread t = new Thread(new Runnable() { //enables directoryLabel to change while files are loading
	        @Override
	        public void run() {
	        	
	        	buttonEnabler.disableButtons();
	        	
	        	searchTerm = searchField.getText().toLowerCase();
	    		searchAll();
	    		searchField.setText("");
	    		
	    		buttonEnabler.enableButtons();
	            
	        }    
	        
	    });
		
		t.start();
		
	}	
	
	public void searchAll() {
		
		this.panelCentre.removeAll();
		
		this.display(this.getResults());
		
		
	}
	
	private ArrayList<Excerpts> getResults() {
		
		ResultsGetter resultsGetter = new ResultsGetter(this.namesAndText, this.searchTerm);
		return resultsGetter.getResults();
		
	}
	
	private void display(ArrayList<Excerpts> results) {
		
		Displayer displayer = new Displayer(this.panelCentre, results);
		displayer.display();
		
	}

}
