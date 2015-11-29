/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import bobkubista.examples.utils.domain.model.api.ActiveApi;
import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericRestActiveProxy;

/**
 * @author Bob
 *
 */
public class MockActiveProxy extends AbstractGenericRestActiveProxy<MockActiveDomainObject, Integer> implements ActiveApi<MockActiveDomainObject, Integer> {

    @Override
    protected String getBasePath() {
        return "";
    }

    @Override
    protected String getBaseUri() {
        return "http://127.0.0.1";
    }

}
