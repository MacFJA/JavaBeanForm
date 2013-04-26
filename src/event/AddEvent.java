package event;

import java.util.Collection;

/**
 * This event is call when the user request a new object (from a {@link Collection} or a <code>null</code> object)
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
public interface AddEvent {
	/**
	 * The event called on object creation
	 * @param newClassType The class of the new object
	 * @return The newly created object
	 */
	public Object onAdd(Class<?> newClassType);
}
