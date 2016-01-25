/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;

/**
 * @author Bob
 *
 */
public abstract class AbstractActiveJerseyIT<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractFunctionalJerseyIT<TYPE, ID, COL> {
    /**
     * Test if getAllActive works
     */
    @Test
    @DatabaseSetup(value = "/dataset/given/FacadeIT.xml")
    public void shouldGetAllActive() {
        final COL response = this.target("/active")
                .request()
                .get(this.getCollectionClass());
        this.checkResponseGetAll(response, this.expectedSize());
    }
}
