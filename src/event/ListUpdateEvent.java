package event;

import java.util.Collection;

/**
 * <i><b>[Intern]</b></i> This event is call when the user change a value in a {@link Collection}
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
public interface ListUpdateEvent {
	/**
	 * <i><b>[Intern]</b></i> The event called when an item of a {@link Collection} is updated
	 * @param index The index in the {@link Collection}
	 * @param newValue The new value of the item
	 */
	void onItemChange(int index, Object newValue);
}
