package folderselection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import application.ComponentEnabler;
import textextraction.DOCTextExtractor;
import textextraction.DOCXTextExtractor;
import textextraction.HTMLTextExtractor;
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
	private JProgressBar progressBar;
	private PDFTextExtractor pdfTextExtractor;
	private TXTTextExtractor txtTextExtractor;
	private DOCTextExtractor docTextExtractor;
	private DOCXTextExtractor docxTextExtractor;
	private HTMLTextExtractor htmlTextExtractor;
	
	public SelectFolderListener(JLabel directoryLabel, HashMap<String, ArrayList<String>> namesAndText, 
			ComponentEnabler componentEnabler, JFrame frame, JTextField searchField, JProgressBar progressBar) {
		
		this.directoryLabel = directoryLabel;
		this.fileNamesAndText = namesAndText;
		this.buttonEnabler = componentEnabler;
		this.frame = frame;
		this.searchField = searchField;
		this.progressBar = progressBar;
		this.pdfTextExtractor = new PDFTextExtractor();
		this.txtTextExtractor = new TXTTextExtractor();
		this.docTextExtractor = new DOCTextExtractor();
		this.docxTextExtractor = new DOCXTextExtractor();
		this.htmlTextExtractor = new HTMLTextExtractor();
		
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
	        			
	        			progressBar.setVisible(true);
	                    progressBar.setMaximum(chosenDirectory.listFiles().length);
	        			progressBar.setValue(0);
	                    
	        			addContentsToMap();
		        		displayFileName();
		        		
		        		progressBar.setVisible(false);
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
		
		this.progressBar.setValue(this.progressBar.getValue() + 1);
		
		if (file.isFile()) {
			String fileName = file.toString();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
		
			this.directoryLabel.setText("loading " + file.getName());
			
			switch (fileType) {
			case (".pdf"):
				this.fileNamesAndText.put(file.getName(), this.pdfTextExtractor.getText(file));
				break;
			case (".doc"):
				this.fileNamesAndText.put(file.getName(), this.docTextExtractor.getText(file));
				break;
			case (".docx"):
				this.fileNamesAndText.put(file.getName(), this.docxTextExtractor.getText(file));
				break;
			case (".html"):
				this.fileNamesAndText.put(file.getName(), this.htmlTextExtractor.getText(file));
				break;
			default:
				this.fileNamesAndText.put(file.getName(), this.txtTextExtractor.getText(file));
				break;
			}
		}
	}
	
	private void displayFileName() {
		
		String directoryString = this.chosenDirectory.toString();
		
		directoryLabel.setText(directoryString);
		frame.setTitle("Excerpter - " + directoryString.substring(directoryString.lastIndexOf("\\") + 1));
		
	}
	
}
