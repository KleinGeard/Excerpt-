package application;


import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;

import search.SearchListener;



public class Window {

	private JFrame frame;
	private JPanel panel_top;
	private JPanel panel_centre;
	private JPanel panel_top_west;
	private JPanel panel_top_east;
	private JTextField searchField;
	private JButton btnSearch;
	private JButton btnSelectDirectory;
	private JLabel directoryLabel;
	private HashMap<String, ArrayList<String>> namesAndText = new HashMap<>();

	public void initialize() {

		this.addTheme();
		this.initialiseFrame();
		this.initialiseTopPanel();
		this.initialiseCentrePanel();
		this.addActionListeners();
		frame.setVisible(true);

	}
	
	private void addTheme() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void initialiseFrame() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(screenSize.width/2,screenSize.height/2);
		this.frame.setExtendedState(this.frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
	}
	
	private void initialiseTopPanel() {
		
		this.panel_top = new JPanel();
		this.frame.getContentPane().add(this.panel_top, BorderLayout.NORTH);	
		this.panel_top.setLayout(new BorderLayout(0, 0));
		
		this.initialiseTopWestPanelComponents();
		this.initialiseTopEastPanelComponents();
		
	}
	
	private void initialiseTopWestPanelComponents() {
		
		this.panel_top_west = new JPanel();
		this.panel_top.add(this.panel_top_west, BorderLayout.WEST);
			
		this.btnSelectDirectory = new JButton("Select Directory");
		this.panel_top_west.add(this.btnSelectDirectory);
			
		this.directoryLabel = new JLabel();
		this.panel_top_west.add(this.directoryLabel);
		
	}
	
	private void initialiseTopEastPanelComponents() {
		
		this.panel_top_east = new JPanel();
		this.panel_top.add(this.panel_top_east, BorderLayout.EAST);
		
		this.searchField = new JTextField();
		this.searchField.setColumns(10);
		this.panel_top_east.add(this.searchField);
		
		this.btnSearch = new JButton("Search");
		this.panel_top_east.add(this.btnSearch);
		
	}
	
	private void initialiseCentrePanel() {
		
		this.panel_centre = new JPanel();
		this.panel_centre.setBackground(Color.WHITE);
		this.frame.getContentPane().add(this.panel_centre, BorderLayout.CENTER);

		
	}
	
	private void addActionListeners() {
		
		SelectFolderListener selectDirectoryFolderListener = new SelectFolderListener(this.directoryLabel, this.namesAndText);
		this.btnSelectDirectory.addActionListener(selectDirectoryFolderListener);
		
		SearchListener searchListener = new SearchListener(this.namesAndText, this.panel_centre, this.searchField);
		this.btnSearch.addActionListener(searchListener);
		this.searchField.addActionListener(searchListener);
		
	}

}

