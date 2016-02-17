package application;

import java.util.ArrayList;

import javax.swing.JComponent;

public class ComponentEnabler {

	private ArrayList<JComponent> components;
	
	public ComponentEnabler() {
		
		this.components = new ArrayList<>();
		
	}
	
	public void addComponent(JComponent button) {
		
		this.components.add(button);
		
	}
	
	public void disableComponents() {
		
		for (JComponent component : this.components)
			component.setEnabled(false);
		
	}
	
	public void enableComponents() {
		
		for (JComponent component : this.components)
			component.setEnabled(true);
		
	}
	
	

}
