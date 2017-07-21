package bobkubista.examples.utils.service.jpa.persistance.services;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import bobkubista.examples.utils.domain.model.api.ActiveSearchBean;
import bobkubista.examples.utils.domain.model.api.SearchBean;
import bobkubista.examples.utils.service.jpa.persistance.dao.GenericActiveDAO;
import bobkubista.examples.utils.service.jpa.persistance.entity.AbstractGenericActiveEntity;

/**
 *
 * @author bkubista
 *
 * @param <TYPE>
 *            {@link AbstractGenericActiveEntity}
 * @param <ID>
 *            identifier
 */
@Transactional
public interface ActiveEntityService<TYPE extends AbstractGenericActiveEntity>
		extends FunctionalIdentifiableEntityService<TYPE> {

	/**
	 *
	 * @return the amount of all active entities
	 */
	public default Long count(final ActiveSearchBean search) {
		return this.getDAO()
				.count(search);
	}

	@Override
	public abstract GenericActiveDAO<TYPE> getDAO();

	/**
	 *
	 * @param search
	 *            {@link SearchBean}
	 * @return all active entities of <code>TYPE</code>
	 */
	default Collection<TYPE> getAll(final ActiveSearchBean search) {
		return this.getDAO()
				.findAll(search);
	}

}
