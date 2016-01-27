/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields of entities as sortable. This maps the fields of
 * the api object to the entity object fields
 *
 * @author Bob
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
    /**
     *
     * @return the fieldname of the api field to sort for. Default is the newest
     *         insertion date
     */
    String fieldName() default "insertedDate";
}
