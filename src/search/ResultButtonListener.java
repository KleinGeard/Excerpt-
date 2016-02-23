package search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ResultButtonListener implements ActionListener {

	private Excerpts excerpts;
	private JPanel panelCentreCentre;
	private JTextField searchField;
	
	public ResultButtonListener(Excerpts excerpts, JPanel panelCentreCentre, JTextField searchField) {

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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JList getResultList() {
		
		 return new JList(excerpts.getDisplayableArray().toArray());
		
	}

}
