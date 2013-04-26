package ui;

import java.awt.Component;

import event.AddEvent;
import event.UpdateEvent;

public interface UI {
	public void setUpdateEvent(UpdateEvent updateEvent);
	public void setAddEvent(AddEvent addEvent);
	public Component getComponent();
	public void setValueOfUI(Object value);
}
