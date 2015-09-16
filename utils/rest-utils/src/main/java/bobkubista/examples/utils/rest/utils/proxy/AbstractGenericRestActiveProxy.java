/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            Identifier
 */
public abstract class AbstractGenericRestActiveProxy<TYPE extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable>
        extends AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID>implements ActiveApi<TYPE, ID> {

    @Override
    public Response getAllActive() {
        return this.getRequest("active").get();
    }

}
