package bobkubista.examples.utils.domain.model.domainmodel.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bob {@link ConstraintValidator} for {@link XorFields} and
 *         {@link Object}
 */
public class XorFieldsValidator implements ConstraintValidator<XorFields, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(XorFieldsValidator.class);

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final XorFields constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, this.firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, this.secondFieldName);

            return firstObj == null && secondObj != null || firstObj != null && secondObj == null;
        } catch (final Exception ignore) {
            LOGGER.debug(ignore.getMessage(), ignore);
        }
        return true;
    }
}
