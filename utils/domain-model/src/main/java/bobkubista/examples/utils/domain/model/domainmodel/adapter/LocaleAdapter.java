/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.adapter;

import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * {@link XmlAdapter} for {@link String} to {@link Locale} Can be used with
 * {@link javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter}
 * <P>
 * Examlpe:
 * <P>
 * <code> @XmlJavaTypeAdapter(LocaleAdapter.class)<BR>private Locale locale;</code>
 *
 * @author bkubista
 *
 */
public class LocaleAdapter extends XmlAdapter<String, Locale> {

    @Override
    public String marshal(final Locale v) throws Exception {
        String result = null;
        if (v != null) {
            result = v.getLanguage();
        }
        return result;
    }

    @Override
    public Locale unmarshal(final String v) throws Exception {
        Locale result = null;
        if (v != null) {
            result = new Locale(v);
        }
        return result;
    }

}
