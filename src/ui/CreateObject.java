package ui;

import javax.swing.JButton;

import event.AddEvent;
import event.UpdateEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateObject extends JButton {
	private Class<?> objectClass;
	private AddEvent addEvent;
	private UpdateEvent updateEvent;
	
	public CreateObject(AddEvent addEvent, UpdateEvent updateEvent) {
		this();
		this.addEvent = addEvent;
		this.updateEvent = updateEvent;
	}
	
	public CreateObject() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(addEvent != null && updateEvent != null)
					updateEvent.onChange(addEvent.onAdd(objectClass));
			}
		});
		setText("Create...");
	}

	/**
	 * @return the objectClass
	 */
	public final Class<?> getObjectClass() {
		return objectClass;
	}

	/**
	 * @param objectClass the objectClass to set
	 */
	public final void setObjectClass(Class<?> objectClass) {
		this.objectClass = objectClass;
	}

	/**
	 * @return the addEvent
	 */
	public final AddEvent getAddEvent() {
		return addEvent;
	}

	/**
	 * @param addEvent the addEvent to set
	 */
	public final void setAddEvent(AddEvent addEvent) {
		this.addEvent = addEvent;
	}

	/**
	 * @return the updateEvent
	 */
	public final UpdateEvent getUpdateEvent() {
		return updateEvent;
	}

	/**
	 * @param updateEvent the updateEvent to set
	 */
	public final void setUpdateEvent(UpdateEvent updateEvent) {
		this.updateEvent = updateEvent;
	}

}
