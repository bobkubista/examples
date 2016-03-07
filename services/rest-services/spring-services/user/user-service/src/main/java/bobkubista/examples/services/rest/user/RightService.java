/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob
 *
 */
@Service
@Transactional
public class RightService implements ActiveEntityService<Rights, Long> {

	@Autowired
	private RightDao dao;

	@Override
	public RightDao getDAO() {
		return this.dao;
	}

}
