/**
 *
 */
package bobkubista.examples.services.api.email.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import bobkubista.examples.services.api.email.model.DateReplacement;
import bobkubista.examples.services.api.email.model.EmailContext;
import bobkubista.examples.services.api.email.model.LinkReplacement;
import bobkubista.examples.services.api.email.model.EmailContext.EmailBuilder;

/**
 * @author Bob
 *
 */
public class EmailContextTest {

    @Test
    public void testBuilder() throws URISyntaxException {
        final EmailContext emailContext = new EmailBuilder("bla@foo.bar", "foobar").addReplacement(new DateReplacement(new Date()))
                .addReplacement(new LinkReplacement(new URI("http://bla.bla"))).build();

        Assert.assertNotNull(emailContext);
        Assert.assertEquals("bla@foo.bar", emailContext.getRecipient());
        Assert.assertEquals("foobar", emailContext.getSubject());
        Assert.assertEquals(3, emailContext.getReplacements().size());
    }

}
