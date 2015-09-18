/**
 *
 */
package bobkubista.examples.services.api.email;

import java.util.Date;

/**
 * @author Bob
 *
 */
public class DateReplacement implements Replacement {

    private static final String DATE = "date";

    private final Date value;

    public DateReplacement(final Date date) {
        this.value = date;
    }

    @Override
    public String getConstant() {
        return DATE;
    }

    @Override
    public String getValue() {
        return this.value.toString();
    }

}
