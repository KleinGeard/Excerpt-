package search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NameButtonListener implements ActionListener {

	private Excerpts excerpts;
	private JPanel panelCentreCentre;
	
	public NameButtonListener(Excerpts excerpts, JPanel panelCentreCentre) {

		this.excerpts = excerpts;
		this.panelCentreCentre = panelCentreCentre;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.panelCentreCentre.removeAll();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JScrollPane resultScroller = new JScrollPane(new JList(excerpts.getDisplayableArray().toArray()));
		resultScroller.getVerticalScrollBar().setUnitIncrement(40);
		resultScroller.setPreferredSize(new Dimension(this.panelCentreCentre.getWidth(), 58));
		
		this.panelCentreCentre.add(resultScroller);
		
		this.panelCentreCentre.repaint();
		this.panelCentreCentre.revalidate();
		
	}

}
