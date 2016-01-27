/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bob
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
    String fieldName() default "";
}
