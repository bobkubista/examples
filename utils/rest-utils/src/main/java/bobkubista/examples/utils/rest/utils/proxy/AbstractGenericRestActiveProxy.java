/**
 *
 */
package bobkubista.examples.utils.rest.utils.proxy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.api.ApiConstants;
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
        extends AbstractGenericRestFunctionalIdentifiableProxy<TYPE, ID> implements ActiveApi<TYPE, ID> {

	@Override
	public Response getAllActive(final List<String> sort, final Integer page, final Integer maxResults) {
		final Map<String, Object> params = new HashMap<>();
		if (CollectionUtils.isNotEmpty(sort)) {
			params.put(ApiConstants.SORT, sort);
		}
		if (page != null) {
			params.put(ApiConstants.PAGE, page);
		}
		if (maxResults != null) {
			params.put(ApiConstants.MAX, maxResults);
		}
		return this.getRequest(this.getServiceWithQueryParams(params, "active")).get();
	}

}
