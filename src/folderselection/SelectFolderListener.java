package folderselection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import application.ComponentEnabler;
import textextraction.DOCTextExtractor;
import textextraction.DOCXTextExtractor;
import textextraction.PDFTextExtractor;
import textextraction.TXTTextExtractor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFolderListener implements ActionListener {

	private JLabel directoryLabel;
	private HashMap<String, ArrayList<String>> fileNamesAndText;
	private ComponentEnabler buttonEnabler;
	private JFrame frame;
	private JTextField searchField;
	private JFileChooser fileChooser = new JFileChooser();
	private File chosenDirectory;
	private String lastChosenDirectory = "";
	private PDFTextExtractor pdfTextExtractor;
	private TXTTextExtractor txtTextExtractor;
	private DOCTextExtractor docTextExtractor;
	private DOCXTextExtractor docxTextExtractor;
	
	public SelectFolderListener(JLabel directoryLabel, HashMap<String, ArrayList<String>> namesAndText, 
			ComponentEnabler componentEnabler, JFrame frame, JTextField searchField) {
		
		this.directoryLabel = directoryLabel;
		this.fileNamesAndText = namesAndText;
		this.buttonEnabler = componentEnabler;
		this.frame = frame;
		this.searchField = searchField;
		this.pdfTextExtractor = new PDFTextExtractor();
		this.txtTextExtractor = new TXTTextExtractor();
		this.docTextExtractor = new DOCTextExtractor();
		this.docxTextExtractor = new DOCXTextExtractor();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Thread t = new Thread(new Runnable() { //enables directoryLabel to change while files are loading
	        @Override
	        public void run() {
	        	
	        	buttonEnabler.disableComponents();
	        	
	        	runFileChooser();
	        	if (chosenDirectory != null) {
	        		if (!chosenDirectory.toString().equals(lastChosenDirectory.toString())) {
	        			addContentsToMap();
		        		displayFileName();
	        		}
	        	}
	        	buttonEnabler.enableComponents();
	        	searchField.requestFocus();
	        	
	        }    
	        
	    });
		
		t.start();
		
	}

	private void runFileChooser() {
		
		if (this.chosenDirectory != null)
			this.lastChosenDirectory = this.chosenDirectory.toString();
		
		this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = this.fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
            this.chosenDirectory = this.fileChooser.getSelectedFile();
        
        
	}
	
	private void addContentsToMap() {
		
		this.fileNamesAndText.clear();	
		for (File file : this.chosenDirectory.listFiles())
			this.addContent(file);
		
	}
	
	public void addContent(File file) {
		
		if (file.isFile()) {
		String fileName = file.toString();
		String fileType = fileName.substring(fileName.lastIndexOf('.'));
		
		this.directoryLabel.setText("loading " + file.getName());
		if (fileType.equals(".pdf"))
			this.fileNamesAndText.put(file.getName(), this.pdfTextExtractor.getText(file));
		else if (fileType.equals(".txt")) 
			this.fileNamesAndText.put(file.getName(), this.txtTextExtractor.getText(file));
		else if (fileType.equals(".doc"))
			this.fileNamesAndText.put(file.getName(), this.docTextExtractor.getText(file));
		else if (fileType.equals(".docx")) 
			this.fileNamesAndText.put(file.getName(), this.docxTextExtractor.getText(file));
		}
	}
	
	private void displayFileName() {
		
		String directoryString = this.chosenDirectory.toString();
		
		directoryLabel.setText(directoryString);
		frame.setTitle("Excerpter - " + directoryString.substring(directoryString.lastIndexOf("\\") + 1));
		
	}
	
}
