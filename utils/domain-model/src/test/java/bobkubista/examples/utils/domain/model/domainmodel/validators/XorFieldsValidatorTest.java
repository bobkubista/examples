/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.validators;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class XorFieldsValidatorTest implements ConstraintValidatorFactory {

    private Validator validator;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> arg0) {
        if (arg0 == XorFieldsValidator.class) {
            return (T) new XorFieldsValidator();
        }
        throw new IllegalArgumentException("expecting XorFieldsValidator!");
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> arg0) {
        // do nothing

    }

    @Before
    public void setUp() throws Exception {

        // see
        // https://docs.jboss.org/hibernate/validator/5.2/reference/en-US/html/chapter-bootstrapping.html#_constraintvalidatorfactory
        final Configuration<?> config = Validation.byDefaultProvider().configure();
        config.constraintValidatorFactory(this);

        final ValidatorFactory factory = config.buildValidatorFactory();

        this.validator = factory.getValidator();
    }

    @Test
    public void testIsValidFirstNull() {
        // arrange
        final MockXorFieldsObject bean = new MockXorFieldsObject("First", null);
        // act
        final Set<ConstraintViolation<MockXorFieldsObject>> constraintViolations = this.validator.validate(bean);
        // assert
        Assert.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void testIsValidFirstSecond() {
        // arrange
        final MockXorFieldsObject bean = new MockXorFieldsObject(null, null);
        // act
        final Set<ConstraintViolation<MockXorFieldsObject>> constraintViolations = this.validator.validate(bean);
        // assert
        Assert.assertFalse(constraintViolations.isEmpty());
    }

    @Test
    public void testIsValidNullNull() {
        // arrange
        final MockXorFieldsObject bean = new MockXorFieldsObject("First", "Second");
        // act
        final Set<ConstraintViolation<MockXorFieldsObject>> constraintViolations = this.validator.validate(bean);
        // assert
        Assert.assertFalse(constraintViolations.isEmpty());
    }

    @Test
    public void testIsValidNullSecond() {
        // arrange
        final MockXorFieldsObject bean = new MockXorFieldsObject(null, "Second");
        // act
        final Set<ConstraintViolation<MockXorFieldsObject>> constraintViolations = this.validator.validate(bean);
        // assert
        Assert.assertTrue(constraintViolations.isEmpty());
    }

}
