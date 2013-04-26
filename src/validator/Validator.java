package validator;

/**
 * Interface for value update validation
 * @author MacFJA
 * @version 1.0 (27/01/2013)
 */
public interface Validator {
	/**
	 * Validate a new value
	 * @param oldValue The previous value
	 * @param newValue The new value
	 * @return <code>true</code> if the new value is valid, <code>false</code> otherwise
	 */
	public boolean validate(Object oldValue, Object newValue);
}
