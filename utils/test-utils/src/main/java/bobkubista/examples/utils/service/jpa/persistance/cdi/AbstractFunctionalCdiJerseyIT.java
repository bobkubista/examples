/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.cdi;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 * @author Bob
 *
 */
public abstract class AbstractFunctionalCdiJerseyIT<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableCdiJerseyIT<TYPE, ID, COL> {
    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetByFunctionalId() {
        this.checkSingle(this.target("/functionId/" + this.getFunctionalId()).request().get(this.getSingleClass()));
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
