package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.RestConstants;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 *
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractFunctionalJerseyIT<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableJerseyIT<TYPE, ID, COL> {

    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByFunctionalId() {
        final Response response = this.target("/functionId/" + this.getFunctionalId())
                .request()
                .get();
        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("private, max-age=600", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        this.checkSingle(response.readEntity(this.getSingleClass()));
    }

    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByFunctionalIdPreconditionsMet() {
        final Response response = this.target("/functionId/" + this.getFunctionalId())
                .request()
                .header(HttpHeaders.IF_MODIFIED_SINCE, Date.from(Instant.now()))
                .get();
        Assert.assertEquals(Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByFunctionalIdPreconditionsNotMet() {
        final Response response = this.target("/functionId/" + this.getFunctionalId())
                .request()
                .header(HttpHeaders.IF_MODIFIED_SINCE, Date.from(Instant.EPOCH))
                .get();
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("private, max-age=600", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        this.checkSingle(response.readEntity(this.getSingleClass()));
    }

    /**
     * Test getIdByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetIdByFunctionalId() {
        final Response response = this.target("/id/" + this.getFunctionalId())
                .request()
                .get();

        Assert.assertNotNull(response.getHeaderString(HttpHeaders.CACHE_CONTROL));
        Assert.assertEquals("private, max-age=600", response.getHeaderString(HttpHeaders.CACHE_CONTROL));

        final String actual = response.readEntity(String.class);

        Assert.assertEquals(this.getId()
                .toString(), actual);
    }

    /**
     * Test if create works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldNotCreate() {
        final TYPE domainObject = this.create();
        domainObject.setFunctionalId(this.getFunctionalId());
        final Response response = this.target("/")
                .request()
                .post(Entity.xml(domainObject));
        try {
            Assert.assertNotNull(response);
            Assert.assertEquals(RestConstants.UNPROCESSABLE_ENTITY, response.getStatus());
        } finally {
            response.close();
        }
    }

    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldNotGetByFunctionalId() {
        final Response response = this.target("/functionId/notthere")
                .request()
                .get(Response.class);
        try {
            Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        } finally {
            response.close();
        }
    }

    /**
     *
     * @return The functionalId
     */
    protected abstract String getFunctionalId();

    /**
     *
     * @return a partion ID to search for
     */
    protected abstract String getPartionFunctionalId();
}
