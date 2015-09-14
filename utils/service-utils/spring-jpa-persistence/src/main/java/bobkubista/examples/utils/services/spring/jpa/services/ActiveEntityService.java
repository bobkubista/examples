package bobkubista.examples.utils.services.spring.jpa.services;

import java.io.Serializable;
import java.util.Collection;

import bobkubista.examples.utils.services.spring.jpa.dao.ActiveEntityDao;
import bobkubista.examples.utils.services.spring.jpa.entity.ActiveEntity;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link ActiveEntity}
 * @param <ID>
 *            identifier
 */
public interface ActiveEntityService<TYPE extends ActiveEntity<ID>, ID extends Serializable> extends FunctionalIdentifiableEntityService<TYPE, ID> {

	/**
	 *
	 * @return all active entities of <code>TYPE</code>
	 */
	default Collection<TYPE> getAllActive() {
		return this.getDAO().findAllActive("id");
	}

	@Override
	public abstract ActiveEntityDao<TYPE, ID> getDAO();
}
