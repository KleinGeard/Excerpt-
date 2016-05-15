package search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import search.results.Page;

public class ResultButtonListener implements ActionListener {

	private Page excerpts;
	private JPanel panelCentreCentre;
	private JTextField searchField;
	
	public ResultButtonListener(Page excerpts, JPanel panelCentreCentre, JTextField searchField) {

		this.excerpts = excerpts;
		this.panelCentreCentre = panelCentreCentre;
		this.searchField = searchField;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.panelCentreCentre.removeAll();
		
		JScrollPane resultScroller = this.getResultScroller();
		this.panelCentreCentre.add(resultScroller);
		
		this.panelCentreCentre.repaint();
		this.panelCentreCentre.revalidate();
		
		this.searchField.requestFocus();
		
	}
	
	
	
	private JScrollPane getResultScroller() {
		
		JScrollPane resultScroller = new JScrollPane(this.getResultList());
		resultScroller.getVerticalScrollBar().setUnitIncrement(40);
		resultScroller.setPreferredSize(new Dimension(this.panelCentreCentre.getWidth(), 58));
		
		return resultScroller;
		
	}
	
	private JList<Object> getResultList() {
		
		return new JList<Object>(excerpts.getDisplayableArray().toArray());
		
	}

}
