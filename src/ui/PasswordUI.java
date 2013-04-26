package ui;

import java.awt.Component;

import javax.swing.JPasswordField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import event.AddEvent;
import event.UpdateEvent;

public class PasswordUI extends JPasswordField implements UI {
	private String previousValue = "";
	private UpdateEvent updateEvent;
	
	public PasswordUI() {
		addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				String value = String.valueOf(getPassword());
				if(value.equals(previousValue)) return;
				previousValue = value;
				if(updateEvent != null) updateEvent.onChange(value);
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
		String actualValue = (String) value;
		previousValue = actualValue;
		setText(actualValue);
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {}

}
