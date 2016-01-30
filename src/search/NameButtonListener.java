package search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NameButtonListener implements ActionListener {

	private Excerpts excerpts;
	private JPanel panel_centre_centre;
	
	public NameButtonListener(Excerpts excerpts, JPanel panel_centre_centre) {

		this.excerpts = excerpts;
		this.panel_centre_centre = panel_centre_centre;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.panel_centre_centre.removeAll();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JScrollPane resultScroller = new JScrollPane(new JList(excerpts.getArrayOfLinesInAllExcerpts().toArray()));
		resultScroller.getVerticalScrollBar().setUnitIncrement(40);
		resultScroller.setPreferredSize(new Dimension(this.panel_centre_centre.getWidth(), 58));
		
		this.panel_centre_centre.add(resultScroller);
		
		this.panel_centre_centre.repaint();
		this.panel_centre_centre.revalidate();
		
	}

}
