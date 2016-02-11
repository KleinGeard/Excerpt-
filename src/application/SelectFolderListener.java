package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFolderListener implements ActionListener {

	private JLabel directoryLabel;
	private File chosenDirectory;
	private JFileChooser fileChooser = new JFileChooser();
	private HashMap<String, ArrayList<String>> fileNamesAndText;
	private BufferedReader reader;
	private ButtonEnabler buttonEnabler;
	
	public SelectFolderListener(JLabel directoryLabel, HashMap<String, ArrayList<String>> namesAndText, ButtonEnabler buttonEnabler) {
		
		this.directoryLabel = directoryLabel;
		this.fileNamesAndText = namesAndText;
		this.buttonEnabler = buttonEnabler;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Thread t = new Thread(new Runnable() { //enables directoryLabel to change while files are loading
	        @Override
	        public void run() {
	        	
	        	buttonEnabler.disableButtons();
	        	
	        	runFileChooser(); 
	            addContentsToMap();
	            
	            directoryLabel.setText(chosenDirectory.toString());
	        	
	            buttonEnabler.enableButtons();
	            
	        }    
	        
	    });
		
		t.start();
		
        
        
	}

	private void runFileChooser() {
		
		this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = this.fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
            this.chosenDirectory = this.fileChooser.getSelectedFile();
		
	}
	
	private void addContentsToMap() {
		
		this.fileNamesAndText.clear();
		
		for (File file : this.chosenDirectory.listFiles())
			if (file.toString().contains(".txt")) 
				this.addContent(file);
		
	}
	
	public void addContent(File file) {
		
		this.directoryLabel.setText("loading " + file.getName());
		this.fileNamesAndText.put(file.getName(), this.getLinesForCurrentFile(file));
		
	}
	
	private ArrayList<String> getLinesForCurrentFile(File file) {
		
		ArrayList<String> lines = new ArrayList<>();
		this.addLines(file, lines);
		
		return lines;
		
	}
	
	private void addLines(File file, ArrayList<String> lines) {
		
		String line;
		
		try {
			
			this.reader = new BufferedReader(new FileReader(file));
			
			while((line = this.reader.readLine()) != null) 
				lines.add(line);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
