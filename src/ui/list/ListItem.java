package ui.list;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.StringUI;
import ui.UI;
import event.ListUpdateEvent;
import event.RemoveEvent;
import event.UpdateEvent;

public class ListItem extends JPanel {
	private JPanel content;
	private RemoveEvent removeEvent;
	private int index;
	private ListUpdateEvent updateEvent;
	private Class<? extends UI> itemUI = StringUI.class;

	/**
	 * Create the panel.
	 */
	public ListItem() {
		setLayout(new BorderLayout(0, 0));
		
		JButton button = new JButton("-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(removeEvent != null) removeEvent.onRemove(index);
			}
		});
		add(button, BorderLayout.EAST);
		
		content = new JPanel();
		add(content, BorderLayout.CENTER);
		content.setLayout(new BorderLayout(0, 0));

	}

	public Component getComponent() {
		return this;
	}

	/**
	 * @return the removeEvent
	 */
	public RemoveEvent getRemoveEvent() {
		return removeEvent;
	}

	/**
	 * @param removeEvent the removeEvent to set
	 */
	public void setRemoveEvent(RemoveEvent removeEvent) {
		this.removeEvent = removeEvent;
	}

	public void setUpdateEvent(ListUpdateEvent updateEvent) {
		this.updateEvent = updateEvent;
	}

	public void setValueOfUI(Object value) {
		content.removeAll();
		try {
			UI ui = itemUI.newInstance();
			ui.setValueOfUI(value);
			ui.setUpdateEvent(new UpdateEvent() {
				@Override
				public void onChange(Object newValue) {
					if(updateEvent != null)
						updateEvent.onItemChange(index, newValue);
				}
			});
			content.add(ui.getComponent(), BorderLayout.CENTER);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
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
	 * @return the updateEvent
	 */
	public ListUpdateEvent getUpdateEvent() {
		return updateEvent;
	}
}
