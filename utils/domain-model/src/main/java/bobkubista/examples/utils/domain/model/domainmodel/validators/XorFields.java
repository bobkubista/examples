/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author bkubista
 *
 *         Validation annotation to validate that 2 fields have the same value.
 *         An array of fields and their matching confirmation fields can be
 *         supplied.
 *
 *         Example, compare 1 pair of fields:
 * @XorFields(first = "password", second = "confirmPassword", message =
 *                  "The password fields must match")
 *
 *                  Example, compare more than 1 pair of
 *                  fields: @XorFields.List({
 * @XorFields(first = "password", second = "confirmPassword", message =
 *                  "The password fields must match"),
 * @XorFields(first = "email", second = "confirmEmail", message =
 *                  "The email fields must match")})
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = XorFieldsValidator.class)
@Documented
public @interface XorFields {
    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         *
         * @return value
         */
        XorFields[]value();
    }

    /**
     * @return The first field
     */
    String first();

    /**
     *
     * @return get groups
     */
    Class<?>[]groups() default {};

    /**
     *
     * @return get the message
     */
    String message() default "{constraints.fieldmatch}";

    /**
     *
     * @return get the payload
     */
    Class<? extends Payload>[]payload() default {};

    /**
     * @return The second field
     */
    String second();
}
