package application;

import java.util.ArrayList;

import javax.swing.JButton;

public class ButtonEnabler {

	private ArrayList<JButton> buttons;
	
	public ButtonEnabler() {
		
		this.buttons = new ArrayList<>();
		
	}
	
	public void addButton(JButton button) {
		
		this.buttons.add(button);
		
	}
	
	public void disableButtons() {
		
		for (JButton button : this.buttons) {
			
			button.setEnabled(false);
			
		}
		
	}
	
	public void enableButtons() {
		
		for (JButton button : this.buttons) {
			
			button.setEnabled(true);
			
		}
		
	}
	
	

}
