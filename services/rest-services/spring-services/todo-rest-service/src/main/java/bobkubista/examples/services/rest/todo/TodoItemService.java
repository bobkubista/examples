/**
 *
 */
package bobkubista.examples.services.rest.todo;

import org.springframework.beans.factory.annotation.Autowired;

import bobkubista.examples.utils.service.jpa.persistance.services.IdentifiableEntityService;

/**
 * @author Bob
 *
 */
public class TodoItemService implements IdentifiableEntityService<TodoEntity, Long> {

    @Autowired
    private TodoEntityDao dao;

    @Override
    public TodoEntityDao getDAO() {
        return this.dao;
    }

}
