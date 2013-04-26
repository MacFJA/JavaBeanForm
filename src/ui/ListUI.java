package ui;

import java.awt.Component;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import ui.list.InternalScrolling;
import ui.list.ListItem;
import validator.NoValidator;
import validator.Validator;
import accessor.CollectionAccessor;
import event.AddEvent;
import event.ListUpdateEvent;
import event.RemoveEvent;
import event.UpdateEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListUI extends JScrollPane implements UI {
	private InternalScrolling content = new InternalScrolling();
	private CollectionAccessor listAccessor = new CollectionAccessor();
	private Class<? extends UI> itemUI = StringUI.class;
	private Validator validator = new NoValidator();
	private AddEvent addEvent;
	private JButton btnAjouter;
	
	public ListUI() {
		setViewportView(content);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(addEvent != null) {
					listAccessor.add(
						addEvent.onAdd(listAccessor.getItemClass())	
					);
					setValueOfUI(listAccessor.getObject());
				}
			}
		});
		btnAjouter.setEnabled(false);
		setColumnHeaderView(btnAjouter);
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
		System.out.println(addEvent+" "+addEvent!=null);
		btnAjouter.setEnabled(addEvent != null);
		this.addEvent = addEvent;
	}

	@Override
	public void setUpdateEvent(UpdateEvent updateEvent) {}

	@Override
	public Component getComponent() {
		return this;
	}

	@Override
	public void setValueOfUI(Object value) {
		@SuppressWarnings("unchecked") Collection<Object> list = (Collection<Object>) value;
		listAccessor.setObject(list);
		content.removeAll();
		for(int tour = 0;tour<list.size();tour++) {
			ListItem li = new ListItem();
			li.setItemUI(itemUI);
			li.setIndex(tour);
			li.setRemoveEvent(new RemoveEvent() {
				@Override
				public void onRemove(int index) {
					listAccessor.remove(index);
					setValueOfUI(listAccessor.getObject());
				}
			});
			li.setUpdateEvent(new ListUpdateEvent() {
				@Override
				public void onItemChange(int index, Object newValue) {
					if(validator.validate(listAccessor.doGet(index), newValue)) {
						listAccessor.doSet(newValue, index);
						setValueOfUI(listAccessor.getObject());
					}
				}
			});
			li.setValueOfUI(listAccessor.doGet(tour));
			content.addLine(li.getComponent());
		}
		updateUI();
	}
	
	
	/**
	 * @return the itemUI
	 */
	public Class<? extends UI> getItemUI() {
		return itemUI;
	}

	/**
	 * @param itemUI the itemUI to set
	 */
	public void setItemUI(Class<? extends UI> itemUI) {
		this.itemUI = itemUI;
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
	 * @return the updateEvent
	 */
	public UpdateEvent getUpdateEvent() { return null; }

	public void setItemClass(Class<?> itemClass) {
		listAccessor.setItemClass(itemClass);
	}
}
