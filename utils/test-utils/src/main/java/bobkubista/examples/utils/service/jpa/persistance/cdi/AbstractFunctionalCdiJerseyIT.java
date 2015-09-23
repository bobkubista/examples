/**
 *
 */
package bobkubista.examples.utils.service.jpa.persistance.cdi;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.FunctionalJerseyIT;

/**
 * @author Bob
 *
 */
public abstract class AbstractFunctionalCdiJerseyIT<TYPE extends AbstractGenericFunctionalIdentifiableDomainObject<ID>, ID extends Serializable, COL extends AbstractGenericDomainObjectCollection<TYPE>>
        extends AbstractIdentifiableCdiJerseyIT<TYPE, ID, COL>implements FunctionalJerseyIT<TYPE, ID, COL> {

}
