package ui;

import java.awt.Component;

import main.Form;

import event.AddEvent;
import event.UpdateEvent;

public class IntoUI implements UI {
	private Form form = new Form();

	@Override
	public void setUpdateEvent(UpdateEvent updateEvent) {}

	@Override
	public Component getComponent() {
		return form;
	}

	@Override
	public void setValueOfUI(Object value) {
		form.makeForm(value);
	}

	@Override
	public void setAddEvent(AddEvent addEvent) {
		form.setAddEvent(addEvent);
	}
}
