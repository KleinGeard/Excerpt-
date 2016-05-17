package application;


import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import folderselection.SelectFolderListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;

import search.SearchListener;

public class Window {

	private JFrame frame;
	private JPanel panelNorth;
	private JPanel panelCentre;
	private JPanel panelSouth;
	private JPanel panelNorthWest;
	private JPanel panelNorthEast;
	private JTextField searchField;
	private JButton btnSearch;
	private JButton btnSelectDirectory;
	private JProgressBar progressBar;
	private JLabel directoryLabel;
	private HashMap<String, ArrayList<String>> namesAndText = new HashMap<>();
	private ComponentEnabler buttonEnabler = new ComponentEnabler();

	public void initialize() {

		this.addTheme();
		this.initialiseFrame();
		this.initialiseNorthPanel();
		this.initialiseCentrePanel();
		this.initialiseSouthPanel();
		this.addActionListeners();
		this.addMnemonics();
		this.frame.setVisible(true);
		this.btnSelectDirectory.requestFocus();
		
	}
	
	private void addTheme() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}			
		
	}
	
	private void initialiseFrame() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		this.frame = new JFrame("Ecerpter");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(screenWidth / 2, screenHeight / 2);
		this.frame.setExtendedState(this.frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/icons/excerpter icon 3.png")));
		
	}
	
	private void initialiseNorthPanel() {
		
		this.panelNorth = new JPanel();
		this.panelNorth.setLayout(new BorderLayout());
		this.frame.getContentPane().add(this.panelNorth, BorderLayout.NORTH);
		
		this.initialiseNorthWestPanelComponents();
		this.initialiseNorthEastPanelComponents();
		
	}
	private void initialiseNorthWestPanelComponents() {
		
		this.panelNorthWest = new JPanel();
		this.panelNorth.add(this.panelNorthWest, BorderLayout.WEST);
			
		this.btnSelectDirectory = new JButton("Select Directory");
		this.panelNorthWest.add(this.btnSelectDirectory);
		this.buttonEnabler.addComponent(this.btnSelectDirectory);
		
		this.directoryLabel = new JLabel();
		this.panelNorthWest.add(this.directoryLabel);
		
	}
	
	private void initialiseNorthEastPanelComponents() {
		
		this.panelNorthEast = new JPanel();
		this.panelNorth.add(this.panelNorthEast, BorderLayout.EAST);
		
		this.searchField = new JTextField();
		this.searchField.setColumns(20);
		this.panelNorthEast.add(this.searchField);
		this.buttonEnabler.addComponent(this.searchField);
		
		this.btnSearch = new JButton("Search");
		this.panelNorthEast.add(this.btnSearch);
		this.buttonEnabler.addComponent(this.btnSearch);
		
	}
	
	private void initialiseCentrePanel() {
		
		this.panelCentre = new JPanel();
		this.panelCentre.setBackground(Color.WHITE);
		this.panelCentre.setLayout(new BorderLayout());
		this.frame.getContentPane().add(this.panelCentre, BorderLayout.CENTER);

	}
	
	private void initialiseSouthPanel() {
		
		this.panelSouth = new JPanel();
		this.panelSouth.setBackground(Color.WHITE);
		this.panelSouth.setLayout(new BorderLayout());
		this.frame.getContentPane().add(this.panelSouth, BorderLayout.SOUTH);
		
		this.progressBar = new JProgressBar();
		this.progressBar.setVisible(false);
		this.panelSouth.add(this.progressBar, BorderLayout.SOUTH);

	}
	
	private void addActionListeners() {
		
		SelectFolderListener selectDirectoryFolderListener = new SelectFolderListener(this.directoryLabel,
				this.namesAndText, this.buttonEnabler, this.frame, this.searchField, this.progressBar);
		this.btnSelectDirectory.addActionListener(selectDirectoryFolderListener);
		
		SearchListener searchListener = new SearchListener(this.namesAndText, this.panelCentre, 
				this.searchField, this.buttonEnabler, this.progressBar);
		this.btnSearch.addActionListener(searchListener);
		this.searchField.addActionListener(searchListener);
		
	}
	
	private void addMnemonics() {
		
		this.btnSearch.setMnemonic('S');
		this.btnSelectDirectory.setMnemonic('D');
		
	}

}

