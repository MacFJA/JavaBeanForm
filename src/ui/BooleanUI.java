package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import event.AddEvent;
import event.UpdateEvent;

public class BooleanUI extends JCheckBox implements UI {
	private UpdateEvent updateEvent;
	
	public BooleanUI() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(updateEvent != null) updateEvent.onChange(BooleanUI.this.isSelected());
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
		setSelected((Boolean) value);
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

}
