package validator;

/**
 * An implementation of {@link Validator} that always accept the new value.
 * @author MacFJA
 * @version 1.0 (27/01/2013)
 */
public class NoValidator implements Validator {
	@Override
	public boolean validate(Object oldValue, Object newValue) {
		return true;
	}

}
