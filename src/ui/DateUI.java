package ui;

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import event.AddEvent;
import event.UpdateEvent;

public class DateUI extends JSpinner implements UI {
	private UpdateEvent updateEvent;

	public DateUI() {
		setModel(new SpinnerDateModel());
		getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				if(updateEvent != null) updateEvent.onChange(DateUI.this.getValue());
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
		if(Date.class.isAssignableFrom(value.getClass())) {			
			setValue(value);
		}
		else if(Calendar.class.isAssignableFrom(value.getClass())) {
			setValue(((Calendar) value).getTime());
		}
		else
			throw new IllegalArgumentException();
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

}
