/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob
 *
 */
@Named
@Transactional
public class RightService implements ActiveEntityService<Rights> {

	@Inject
	private RightDao dao;

	@Override
	public RightDao getDAO() {
		return this.dao;
	}

}
