package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ui.StringUI;
import ui.UI;
import validator.NoValidator;
import validator.Validator;

/**
 * Define the variable associate to the following Getter as a form item.<br>
 * <i>To use on Getter</i>
 * @author MacFJA
 * @version 1.0 (29/01/2013)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBFItem {
	/** The label of the form item */
	String label();
	/** The class of the form item user interface. */
	Class<? extends UI> ui() default StringUI.class;
	/** The class of the validator that validate or not the new value of the variable */
	Class<? extends Validator> validator() default NoValidator.class;
}
