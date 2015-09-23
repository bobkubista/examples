/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;

/**
 * @author Bob
 * @param <TYPE>
 *            {@link AbstractGenericFunctionalIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public interface FunctionalJerseyIT<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends IdentifiableJerseyIT<TYPE, ID, COL> {

    /**
     * Test getByFunctionalId
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public default void shouldGetByFunctionalId() {
        this.checkSingle(this.getTarget("/functionId/" + this.getFunctionalId()).request().get(this.getSingleClass()));
    }

    /**
     *
     * @return The functionalId
     */
    String getFunctionalId();

    /**
     *
     * @return a partion ID to search for
     */
    String getPartionFunctionalId();
}
