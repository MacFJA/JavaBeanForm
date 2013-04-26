package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that search is deep (through superclass and interfaces).<br>
 * <i>To use on class</i>
 * @author MacFJA
 * @version 1.0 (28/01/2013)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JBFHerited {}
