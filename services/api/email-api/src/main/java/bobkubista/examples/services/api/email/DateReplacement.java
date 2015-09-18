/**
 *
 */
package bobkubista.examples.services.api.email;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Bob
 *
 */
public class DateReplacement extends Pair<String, Date> {

    private static final String DATE = "date";

    private static final long serialVersionUID = 2722567114207798899L;

    private Date value;

    public DateReplacement(final Date date) {
        this.value = date;
    }

    @Override
    public String getLeft() {
        return DATE;
    }

    @Override
    public Date getRight() {
        return this.value;
    }

    @Override
    public Date setValue(final Date value) {
        this.value = value;
        return value;
    }

}
