package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * With this annotation you can indicate that the variable associate to the following Getter must be ignore.<br>
 * <i>To use on Getter</i>
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBFIgnore {}
