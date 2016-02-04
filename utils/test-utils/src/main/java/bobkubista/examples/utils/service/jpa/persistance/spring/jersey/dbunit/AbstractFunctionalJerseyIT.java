package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

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
        this.checkSingle(this.target("/functionId/" + this.getFunctionalId())
                .request()
                .get(this.getSingleClass()));
    }

    /**
     * Test getIdByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetIdByFunctionalId() {
        final String actual = this.target("/id/" + this.getFunctionalId())
                .request()
                .get(String.class);

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
            Assert.assertEquals(422, response.getStatus());
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
