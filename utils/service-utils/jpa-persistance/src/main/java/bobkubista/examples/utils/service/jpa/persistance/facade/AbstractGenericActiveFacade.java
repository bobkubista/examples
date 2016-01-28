package bobkubista.examples.utils.service.jpa.persistance.facade;

import java.io.Serializable;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericActiveDomainObject;
import bobkubista.examples.utils.domain.model.domainmodel.identification.AbstractGenericDomainObjectCollection;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;
import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author bkubista
 * @param <DMO>
 *            {@link AbstractGenericActiveDomainObject}
 * @param <ID>
 *            The idertifier
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <DMOL>
 *            {@link AbstractGenericDomainObjectCollection}
 */
public abstract class AbstractGenericActiveFacade<DMO extends AbstractGenericActiveDomainObject<ID>, ID extends Serializable, TYPE extends AbstractGenericActiveEntity<ID>, DMOL extends AbstractGenericDomainObjectCollection<DMO>>
        extends AbstractGenericFunctionalIdentifiableFacade<DMO, TYPE, ID, DMOL>implements ActiveApi<DMO, ID> {

    @Override
    public Response getAllActive(@BeanParam final SearchBean searchBean) {
        return Response.ok(this.getConverter()
                .convertToDomainObject(this.getService()
                        .getAllActive(searchBean.getSort(), searchBean.getPage(), searchBean.getMaxResults())))
                .build();
    }

    @Override
    protected abstract ActiveEntityService<TYPE, ID> getService();

}
