package bobkubista.examples.utils.domain.model.api;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 */
public interface ActiveServerApi<DMO extends AbstractGenericActiveDomainObject>
		extends FunctionalIdentifiableServerApi<DMO> {

	/**
	 *
	 * @param searchBean
	 *            {@link SearchBean} filled with criteria, sorts and limits
	 * @return get all {@link AbstractGenericActiveDomainObject}s
	 */
	default Response getAll(@Valid @BeanParam final ActiveSearchBean searchBean) {
		return IdentifiableServerApi.buildMethodNotAllowedResponse(searchBean);
	}
}
