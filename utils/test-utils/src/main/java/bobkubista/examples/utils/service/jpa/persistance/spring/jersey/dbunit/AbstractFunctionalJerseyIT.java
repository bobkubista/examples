package bobkubista.examples.utils.service.jpa.persistance.spring.jersey.dbunit;

import java.io.Serializable;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericFunctionalIdentifiableDomainObject;
import bobkubista.examples.utils.service.jpa.persistance.FunctionalJerseyIT;

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
        extends AbstractIdentifiableJerseyIT<TYPE, ID, COL>implements FunctionalJerseyIT<TYPE, ID, COL> {

}
