/**
 * Bob Kubista's examples
 */
package bobkubista.examples.utils.rest.utils.mocks;

import bobkubista.examples.utils.rest.utils.proxy.AbstractGenericActiveRestProxy;

/**
 * @author Bob
 *
 */
public class MockActiveProxy extends AbstractGenericActiveRestProxy<MockActiveDomainObject, MockDomainCollection> {

	@Override
	protected MockDomainCollection getAllFallback() {
		return new MockDomainCollection();
	}

	@Override
	protected String getBasePath() {
		return "";
	}

	@Override
	protected String getBaseUri() {
		return "http://127.0.0.1";
	}

	@Override
	protected MockDomainCollection getEmptyCollection() {
		return new MockDomainCollection();
	}

}
