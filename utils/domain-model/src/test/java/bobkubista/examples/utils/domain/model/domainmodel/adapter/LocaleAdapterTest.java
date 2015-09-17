/**
 *
 */
package bobkubista.examples.utils.domain.model.domainmodel.adapter;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class LocaleAdapterTest {

    /**
     * Test method for
     * {@link bobkubista.examples.utils.domain.model.domainmodel.adapter.LocaleAdapter#marshal(java.util.Locale)}
     * .
     *
     * @throws Exception
     */
    @Test
    public void testMarshalLocale() throws Exception {
        final LocaleAdapter adapter = new LocaleAdapter();
        Assert.assertEquals("nl", adapter.marshal(new Locale("NL")));
    }

    /**
     * Test method for
     * {@link bobkubista.examples.utils.domain.model.domainmodel.adapter.LocaleAdapter#unmarshal(java.lang.String)}
     * .
     *
     * @throws Exception
     */
    @Test
    public void testUnmarshalString() throws Exception {
        final LocaleAdapter adapter = new LocaleAdapter();
        Assert.assertEquals(new Locale("NL"), adapter.unmarshal("NL"));
    }

}
