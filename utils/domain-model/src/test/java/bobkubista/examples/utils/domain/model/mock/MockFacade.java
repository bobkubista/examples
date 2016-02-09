package bobkubista.examples.utils.domain.model.mock;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response;

import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheMaxAge;
import bobkubista.examples.utils.domain.model.annotation.http.cache.CacheNo;
import bobkubista.examples.utils.domain.model.api.IdentifiableApi;
import bobkubista.examples.utils.domain.model.domainmodel.identification.GenericTestFunctionalDomainObject;

public class MockFacade implements IdentifiableApi<GenericTestFunctionalDomainObject, Integer> {

    @CacheNo
    @CacheMaxAge(time = 1, unit = TimeUnit.MINUTES)
    @Override
    public Response getByID(final Integer identifier) {
        return IdentifiableApi.super.getByID(identifier);
    }

}
