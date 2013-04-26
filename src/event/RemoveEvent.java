package event;

import java.util.Collection;

/**
 * <i><b>[Intern]</b></i> This event is call when the user removea item of a {@link Collection}
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
public interface RemoveEvent { 
	/**
	 * <i><b>[Intern]</b></i> The event when an item is remove
	 * @param index The index of the item in the {@link Collection}
	 */
	public void onRemove(int index);
}
