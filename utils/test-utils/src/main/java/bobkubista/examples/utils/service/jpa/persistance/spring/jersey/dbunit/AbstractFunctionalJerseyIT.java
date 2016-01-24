package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Ignore;
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
// TODO build test for getIdForFunctionalId, searchByFunctionalId, getAllActive
public abstract class AbstractFunctionalJerseyIT<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableJerseyIT<TYPE, ID, COL> {
    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByFunctionalId() {
        this.checkSingle(this.target("/functionId/" + this.getFunctionalId()).request().get(this.getSingleClass()));
    }

    @Ignore
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetIdByFunctionalId() {
        final ID actual = this.target("/id/" + this.getFunctionalId()).request().get(this.getIdentifierClass());
        Assert.assertEquals(this.getId(), actual);
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
