/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;

import bobkubista.examples.utils.domain.model.api.ActiveClientApi;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
import bobkubista.examples.utils.domain.model.api.SearchBean;
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
        extends AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID>implements ActiveClientApi<TYPE, ID> {

    @Override
    public Response getAllActive(final SearchBean searchBean) {
        final Map<String, Object> params = new HashMap<>();
        if (CollectionUtils.isNotEmpty(searchBean.getSort())) {
            params.put(ApiConstants.SORT, searchBean.getSort());
        }
        if (searchBean.getPage() != null) {
            params.put(ApiConstants.PAGE, searchBean.getPage());
        }
        if (searchBean.getMaxResults() != null) {
            params.put(ApiConstants.MAX, searchBean.getMaxResults());
        }
        return this.getRequest(this.getServiceWithQueryParams(params, "active"))
                .get();
    }

}
