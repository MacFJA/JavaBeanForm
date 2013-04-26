package main;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import ui.CreateObject;
import ui.StringUI;
import ui.UI;
import validator.NoValidator;
import validator.Validator;
import accessor.PropertyAccessor;
import event.AddEvent;
import event.UpdateEvent;

/**
 * The class contain all link between UI/Validator/Events/Object
 * @author MacFJA
 * @version 1.0 (29/01/2013)
 */
public class Editor {
	/** The label of the variable */
	private String label = "";
	/** This object is use to access to the getter/setter of the variable */
	private PropertyAccessor propertyAccessor = new PropertyAccessor();
	/** The UI of the variable */
	private UI ui = new StringUI();
	/** The validator of the object */
	private Validator validator = new NoValidator();
	/** The event send when a new value is requested */
	private AddEvent addEvent = null;
	/** the implementation on the Update event */
	private UpdateEvent updateEvent = new UpdateEvent() {
		@Override
		public void onChange(Object newValue) {
			//Validate the new value
			if(validator.validate(propertyAccessor.doGet(), newValue)) {
				//Set the new value
				propertyAccessor.doSet(newValue);
				setComponent();
			}
			else {
				//Update the UI with the actual value
				valueOfObject();
			}

		}
	};
	private CreateObject create = new CreateObject(addEvent, updateEvent);
	private JPanel content = new JPanel(new BorderLayout());

	public Editor() {
		ui.setUpdateEvent(updateEvent);
		ui.setAddEvent(addEvent);
	}

	/**
	 * Return the component to display
	 * @return The component to display
	 */
	public Component getComponent() {
		return content;
	}
	public void setComponent() {
		Component c = null;
		if(propertyAccessor.doGet() == null) {
			c = create;
		}
		else {
			c = ui.getComponent();
		}
		if(content.getComponentCount() != 0 && content.getComponent(0).equals(c)) return;
		content.removeAll();
		content.add(c, BorderLayout.CENTER);
		content.doLayout();
	}

	public void setObject(Object object) {
		propertyAccessor.setObject(object);
		create.setObjectClass(propertyAccessor.getObjectClass());
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
		create.setAddEvent(addEvent);
	}

	/**
	 * @return the validator
	 */
	public Validator getValidator() {
		return validator;
	}
	/**
	 * @param validator the validator to set
	 */
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the propertyAccessor
	 */
	public PropertyAccessor getPropertyAccessor() {
		return propertyAccessor;
	}
	/**
	 * @param propertyAccessor the propertyAccessor to set
	 */
	public void setPropertyAccessor(PropertyAccessor propertyAccessor) {
		this.propertyAccessor = propertyAccessor;
	}
	/**
	 * @return the ui
	 */
	public UI getUi() {
		return ui;
	}
	/**
	 * @param ui the ui to set
	 */
	public void setUi(UI ui) {
		this.ui = ui;
		ui.setUpdateEvent(updateEvent);
		ui.setAddEvent(addEvent);
	}

	public void valueOfObject() {
		setComponent();
		this.ui.setValueOfUI(propertyAccessor.doGet());
	}

	public boolean isValid() {
		try {
			this.ui.setValueOfUI(propertyAccessor.doGet());
			return this.propertyAccessor.getSetter() != null;
		}
		catch (ClassCastException e) {
			return false;
		}
		catch (IllegalArgumentException e) {
			return false;
		}
	}

}
