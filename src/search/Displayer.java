package search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Displayer {

	private JPanel panelCentre;
	private ArrayList<Excerpts> results;
	private JTextField searchField;
	
	public Displayer(JPanel panelCentre, ArrayList<Excerpts> results, JTextField searchField) {
		
		this.panelCentre = panelCentre;
		this.results = results;
		this.searchField = searchField;
		
	}
	
	public void display() {
		
		if (this.hasMatches()){
			
			JPanel panelCentreCentre = this.createPanelCentreCentre();
			JScrollPane topScroller = this.createTopScroller(panelCentreCentre);		
			this.panelCentre.add(topScroller, BorderLayout.NORTH);
			this.panelCentre.add(panelCentreCentre, BorderLayout.CENTER);
			
		} else {
			
			JPanel panelCentreTop = new JPanel();
			panelCentre.add(panelCentreTop, BorderLayout.NORTH);
			panelCentreTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelCentreTop.setBackground(Color.WHITE);
			
			panelCentreTop.add(new JLabel("No Results Found."));
			
		}
			
		this.panelCentre.revalidate();
		this.panelCentre.repaint();
		
	}
	
	private boolean hasMatches() {
		
		boolean hasMatches = false;
		
		for (Excerpts excerpts : this.results)
			if (excerpts.getNumberOfMatches() > 0)
				hasMatches = true;
		
		return hasMatches;
		
	}
	
	private JPanel createPanelCentreCentre() {
		
		JPanel panelCentreCentre = new JPanel();
		panelCentreCentre.setLayout(new BorderLayout());
		panelCentreCentre.setBackground(Color.WHITE);
		panelCentreCentre.setSize(this.panelCentre.getSize());
		
		return panelCentreCentre;
		
	}
	
	private JScrollPane createTopScroller(JPanel panelCentreCentre) {
		
		JPanel panelTopScroller = this.getPanelTopScroller(panelCentreCentre);
		
		JScrollPane topScroller = new JScrollPane(panelTopScroller);
		topScroller.getHorizontalScrollBar().setUnitIncrement(20);
		topScroller.setPreferredSize(new Dimension(this.panelCentre.getWidth(), 58));
		
		return topScroller;
		
	}
	
	private JPanel getPanelTopScroller(JPanel panelCentreCentre) {
		
		JPanel panelTopScroller = new JPanel();
		panelTopScroller.setLayout(new FlowLayout());
		panelTopScroller.setBackground(Color.WHITE);
		this.addContentsToPanelCentreScroller(panelCentreCentre, panelTopScroller);
		
		return panelTopScroller;
		
	}
	
	private void addContentsToPanelCentreScroller(JPanel panelCentreCentre, JPanel panelCentreScroller) {
		
		for (Excerpts excerpts : this.results) {
			
			if (excerpts.containsExcerpts()) {
				
				JButton nameButton = new JButton(excerpts.getName() + " / matches: " + excerpts.getNumberOfMatches());
				
				ResultButtonListener nameButtonListener = new ResultButtonListener(excerpts, panelCentreCentre, this.searchField);
				nameButton.addActionListener(nameButtonListener);
				
				panelCentreScroller.add(nameButton);
				
			}
			
		}
		
	}

}
