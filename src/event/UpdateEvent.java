package event;

import main.Editor;

/**
 * This event is call when the value of object is changed.<br>
 * <h2>Example of use</h2>
 * In most of case the object that implement this interface is not null but consider that can happen
 * <code><pre> UpdateEvent update;
 * //...
 * if(update != null) update.onChange(theNewValueOfTheObject);</pre></code>
 * This is the unique code that you need to write. The implementation of the method onChange is set in the {@link Editor} class.
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
public interface UpdateEvent {
	/**
	 * The event to call when the object is updated
	 * @param newValue The new value of the object
	 */
	void onChange(Object newValue);
}
