/**
 * Bob Kubista's examples
 */
package bobkubista.examples.services.rest.user;

import org.springframework.beans.factory.annotation.Autowired;

import bobkubista.examples.utils.service.jpa.persistance.services.ActiveEntityService;

/**
 * @author Bob
 *
 */
public class RightService implements ActiveEntityService<Right, Long> {

    @Autowired
    private RightDao dao;

    @Override
    public RightDao getDAO() {
        return this.dao;
    }

}
