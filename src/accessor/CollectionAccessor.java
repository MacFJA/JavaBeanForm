package accessor;

import java.util.Collection;
import java.util.List;

public class CollectionAccessor {
	private Collection<Object> object;
	private Class<?> itemClass;

	/**
	 * @return the object
	 */
	public Collection<Object> getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Collection<Object> object) {
		this.object = object;
	}

	public Boolean doSet(Object newValue, int index) {
		if(index < 0 || index >= object.size()) return false;
		
		if(object instanceof List) {
			((List<Object>)object).set(index, newValue);
		}
		else {
			Object oldValue = doGet(index);
			object.remove(oldValue);
			object.add(newValue);
		}
		
		return true;
	}
	public Object doGet(int index) {
		if(index < 0 || index >= object.size()) return null;
		Object[] all = object.toArray();
		return all[index];
	}

	public Boolean remove(int index) {
		return object.remove(doGet(index));
	}
	
	public Boolean add(Object newObject) {
		return object.add(newObject);
	}

	/**
	 * @return the itemClass
	 */
	public final Class<?> getItemClass() {
		return itemClass;
	}

	/**
	 * @param itemClass the itemClass to set
	 */
	public final void setItemClass(Class<?> itemClass) {
		this.itemClass = itemClass;
	}
}
