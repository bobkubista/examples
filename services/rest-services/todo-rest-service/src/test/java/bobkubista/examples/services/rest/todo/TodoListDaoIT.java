/**
 *
 */
package bobkubista.examples.services.rest.todo;

import bobkubista.examples.utils.service.jpa.persistance.AbstractFunctionalDaoIT;
import bobkubista.examples.utils.service.jpa.persistance.dao.FunctionalIdentifiableEntityDao;

/**
 * @author Bob Kubista
 *
 */
public class TodoListDaoIT extends AbstractFunctionalDaoIT<TodoListEntity, Long> {

	@Override
	protected void checkAssertion(final TodoListEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void checkAssertionUpdated(final TodoListEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected FunctionalIdentifiableEntityDao<TodoListEntity, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getFunctionalId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateEntity(final TodoListEntity entity) {
		// TODO Auto-generated method stub

	}

}
