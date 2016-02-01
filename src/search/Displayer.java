package search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.ButtonEnabler;

public class Displayer {

	private JPanel panelCentre;
	private ArrayList<Excerpts> results;
	
	public Displayer(JPanel panelCentre, ArrayList<Excerpts> results) {
		
		this.panelCentre = panelCentre;
		this.results = results;
		
	}
	
	public void display() {
		
		JPanel panelCentreCentre = this.createPanelCentreCentre();
		JScrollPane topScroller = this.createTopScroller(panelCentreCentre);
		
		this.panelCentre.add(topScroller, BorderLayout.NORTH);
		this.panelCentre.add(panelCentreCentre, BorderLayout.CENTER);
		
		this.panelCentre.revalidate();
		this.panelCentre.repaint();
		
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
				
				NameButtonListener nameButtonListener = new NameButtonListener(excerpts, panelCentreCentre);
				nameButton.addActionListener(nameButtonListener);
				
				panelCentreScroller.add(nameButton);
				
			}
			
		}
		
	}

}
