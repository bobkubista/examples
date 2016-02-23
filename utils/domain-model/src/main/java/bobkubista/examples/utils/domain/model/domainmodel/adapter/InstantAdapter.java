package bobkubista.examples.utils.domain.model.domainmodel.adapter;

import java.time.Instant;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Bob
 *
 */
public class InstantAdapter extends XmlAdapter<String, Instant> {

    @Override
    public String marshal(final Instant value) throws Exception {
        return value.toString();
    }

    @Override
    public Instant unmarshal(final String value) throws Exception {
        return Instant.parse(value);
    }

}
