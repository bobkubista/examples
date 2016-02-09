/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * @author Bob
 * @param <TYPE>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractActiveJerseyIT<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractFunctionalJerseyIT<TYPE, ID, COL> {
    /**
     * Test if getAllActive works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllActive() {
        final Response response = this.target("/active")
                .request()
                .get();

        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("private, max-age=600", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        final COL actual = response.readEntity(this.getCollectionClass());

        this.checkResponseGetAll(actual, this.expectedSize());
    }
}
