package application;

import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				Window window = new Window();
				window.initialize();
					
			}
			
		});
		
	}

}
