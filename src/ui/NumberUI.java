package ui;

import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import event.AddEvent;
import event.UpdateEvent;

public class NumberUI extends JSpinner implements UI {
	private UpdateEvent updateEvent;
	
	public NumberUI() {
		setModel(new SpinnerNumberModel());
		getModel().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent event) {
				if(updateEvent != null) updateEvent.onChange(NumberUI.this.getValue());
			}
		});
	}
	
	@Override
	public void setUpdateEvent(UpdateEvent updateEvent) {
		this.updateEvent = updateEvent;
	}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void setValueOfUI(Object value) {
		setValue(value);
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

}
