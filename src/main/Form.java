package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import event.AddEvent;

import ui.ListUI;
import accessor.PropertyAccessor;
import annotation.JBFHerited;
import annotation.JBFIgnore;
import annotation.JBFItem;

public class Form extends JPanel {
	private List<Editor> editors = new ArrayList<Editor>();
	private List<String> ignores = new ArrayList<String>();
	private int rowCount = 0;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private AddEvent addEvent = null;

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

	public Form() {
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
	}

	public Form(Object ofObject) {
		this();
		makeForm(ofObject);
	}

	public Form ignore(String property) {
		ignores.add(property);
		return this;
	}

	private void addLine(Editor editor) {
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.insets = new Insets(0, 0, 0, 5);
		gbcLabel.anchor = GridBagConstraints.EAST;
		gbcLabel.gridx = 0;
		gbcLabel.gridy = rowCount;
		add(new JLabel(editor.getLabel()), gbcLabel);

		GridBagConstraints gbcValue = new GridBagConstraints();
		gbcValue.fill = GridBagConstraints.HORIZONTAL;
		gbcValue.gridx = 1;
		gbcValue.gridy = rowCount;
		add(editor.getComponent(), gbcValue);

		rowCount++;
		int[] rowHeights = new int[rowCount];
		for(int tour=0; tour < rowCount; tour++) rowHeights[tour] = 0;
		gridBagLayout.rowHeights = rowHeights;
	}

	private void setModel(Class<?> modelClass) {
		editors = new ArrayList<Editor>();
		Method[] methods;
		if(modelClass.isAnnotationPresent(JBFHerited.class)) {
			methods = modelClass.getMethods();
		}
		else {
			methods = modelClass.getDeclaredMethods();
		}

		for(Method m : methods) {
			if(PropertyAccessor.isGetter(m) && !m.isAnnotationPresent(JBFIgnore.class) && !ignores.contains(PropertyAccessor.getVariableName(m, false))) {
				Editor e = new Editor();
				e.getPropertyAccessor().setGetter(m);
				e.setAddEvent(addEvent);

				if(m.isAnnotationPresent(JBFItem.class)) {
					JBFItem item = m.getAnnotation(JBFItem.class);
					e.setLabel(item.label());
					
					Boolean isCollection = Boolean.FALSE;
					
					for(Class<?> c : m.getReturnType().getInterfaces()) {
						if(c.equals(Collection.class)) isCollection = Boolean.TRUE;
					}
					if(isCollection) {
						buildCollectionEditor(e, ((ParameterizedType)m.getGenericReturnType()).getActualTypeArguments()[0], item);
					}
					else
						buildPropertyEditor(e, item);
				}
				else {
					//Use Editor default values
				}

				editors.add(e);
			}
		}
	}

	private void buildPropertyEditor(Editor editor, JBFItem annotation) {
		editor.setLabel(annotation.label());
		try {
			editor.setUi(annotation.ui().newInstance());
			editor.setValidator(annotation.validator().newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void buildCollectionEditor(Editor editor, Type itemClass, JBFItem annotation) {
		editor.setLabel(annotation.label());
		ListUI lui = new ListUI();
		lui.setItemUI(annotation.ui());
		lui.setItemClass((Class<?>)itemClass);
		try {
			lui.setValidator(annotation.validator().newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editor.setUi(lui);
	}

	public void makeForm(Object ofObject) {
		if(ofObject == null) return;
		removeAll();
		setModel(ofObject.getClass());

		for(Editor e : editors) {
			e.setObject(ofObject);
			if(e.isValid()) {
				e.valueOfObject();
				addLine(e);
			}
		}
	}
}
