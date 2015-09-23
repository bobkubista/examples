/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.cdi;

import java.io.Serializable;

import javax.ws.rs.client.WebTarget;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.IdentifiableJerseyIT;

/**
 * @author Bob
 * @param <TYPE>
 *            {@link AbstractGenericIdentifiableDomainObject}
 * @param <ID>
 *            {@link Serializable}
 * @param <COL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractIdentifiableCdiJerseyIT<TYPE extends AbstractGenericIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractBaseCdiJerseyDbUnitTest implements IdentifiableJerseyIT<TYPE, ID, COL> {

    @Override
    public WebTarget getTarget(final String string) {
        return this.target(string);
    }

}
