package bobkubista.examples.utils.service.jpa.persistance;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.domainmodel.identification.DomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.FunctionalIdentifiableDomainObject;

/**
 *
 * @author Bob Kubista
 *
 * @param <TYPE>
 *            {@link FunctionalIdentifiableDomainObject}
 * @param <ID>
 *            the identifier
 * @param <COL>
 *            {@link DomainObjectCollection}
 */
public abstract class AbstractFunctionalJerseyIT<TYPE extends FunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends DomainObjectCollection<TYPE>>
        extends AbstractIdentifiableJerseyIT<TYPE, ID, COL> {

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
