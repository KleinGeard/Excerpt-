package application;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import search.SearchListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Canvas;

public class Main {

	private JFrame frame;
	private JPanel panel_top;
	private JPanel panel_centre;
	private JPanel panel_top_west;
	private JPanel panel_top_east;
	private JTextField searchField;
	private JButton btnSearch;
	private JButton btnSelectDirectory;
	private JLabel directoryLabel;
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmExcertpSize;
	private HashMap<String, ArrayList<String>> namesAndText = new HashMap<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {

		this.addTheme();
		this.initialiseFrame();
		//this.initialiseMenu();
		this.initialiseTopPanel();
		this.initialiseCentrePanel();
		this.addActionListeners();

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
	
	private void initialiseMenu() {
		
		this.menuBar = new JMenuBar();
		this.frame.setJMenuBar(menuBar);
		
		this.mnOptions = new JMenu("Options");
		this.menuBar.add(mnOptions);
		
		this.mntmExcertpSize = new JMenuItem("Excertp Size");
		this.mnOptions.add(mntmExcertpSize);
		
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
		//this.directoryLabel.setColumns(50);
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

