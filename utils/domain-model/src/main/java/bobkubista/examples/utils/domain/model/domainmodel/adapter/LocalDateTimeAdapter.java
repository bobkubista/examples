package bobkubista.examples.utils.domain.model.domainmodel.adapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @see <a href=
 *      "http://stackoverflow.com/questions/29736660/jersey-consuming-parsing-java-8-date-time">
 *      Stack overflow</a>
 * @author Bob
 *
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public String marshal(final LocalDateTime value) throws Exception {
        final Instant instant = value.toInstant(ZoneOffset.UTC);
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    @Override
    public LocalDateTime unmarshal(final String value) throws Exception {
        final Instant instant = Instant.parse(value);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
