package validator;

/**
 * An implementation of {@link Validator} that only accept positive ([0, Infinity[) number. 
 * @author MacFJA
 * @version 1.0 (27/01/2013)
 */
public class PositiveValidator implements Validator {
	@Override
	public boolean validate(Object oldValue, Object newValue) {
		Double value = (Double) newValue;
		return Math.signum(value) > -1;
	}

}
